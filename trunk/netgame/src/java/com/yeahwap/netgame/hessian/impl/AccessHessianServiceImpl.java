package com.yeahwap.netgame.hessian.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;

import com.yeahwap.netgame.Constants;
import com.yeahwap.netgame.hessian.AccessHessianService;
import com.yeahwap.netgame.hessian.domain.AccessLogType;
import com.yeahwap.netgame.hessian.domain.pojo.AccessLog;


/**
 * Create on 2012-10-22 12:28
 * 
 * @author Harry
 * 
 *         <ul>
 *         <li>Title:AccessHessianServiceImpl.java</li>
 *         <li>description: 记录日志</li>
 *         </ul>
 * 
 */

@Service
public class AccessHessianServiceImpl implements AccessHessianService {
	@Autowired
	@Qualifier("statDataSource")
	private DataSource statDataSource;
	@Resource
	private TaskExecutor taskExecutor;

	private final long TIMEOUT = (long) Constants.LOG_TIMEOUT * 1000;
	private final SimpleDateFormat monthFormat = new SimpleDateFormat("yyyyMM");
	private static final Object LOCK = new Object();
	private static final int BATCH_SIZE = Constants.LOG_BATCH_SIZE;
	private final Comparator<AccessLog> MyLogComparator = new LogComparator();
	private AccessLog[] array = new AccessLog[BATCH_SIZE];
	private int arraySize = 0;
	private long lastLogTime = System.currentTimeMillis();

	@PostConstruct
	public void init() {
		createTable();
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000 * 30);
						if (System.currentTimeMillis() - lastLogTime >= TIMEOUT && arraySize > 0) {
							AccessLog[] logs = null;
							int length = 0;
							
							synchronized (LOCK) {
								logs = array;
								length = arraySize;
								array = new AccessLog[BATCH_SIZE];
								arraySize = 0;
							}
							
							lastLogTime = System.currentTimeMillis();
							
							if (null != logs && length > 0) {
								Arrays.sort(logs, MyLogComparator);
								System.out.println("log batch ... length=" + length);
								log(logs, length);
							}
						}
					} catch (Exception e) {}
				}
			}
		});
		t.setDaemon(true);
		t.start();
	}

	class LogComparator implements Comparator<AccessLog> {
		public int compare(AccessLog o1, AccessLog o2) {
			if (o1 == null && o2 != null) {
				return 1;
			}
			if (o1 == null && o2 == null) {
				return 0;
			}
			if (o1 != null && o2 == null) {
				return -1;
			}
			return o1.getType() < o2.getType() ? 1 : o1.getType() == o2.getType() ? 0 : -1;
		}
	}

	private void createTable() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(statDataSource);
		Calendar thisMonth = Calendar.getInstance();
		Calendar nextMonth = Calendar.getInstance();
		nextMonth.add(Calendar.MONTH, 1);		
		String[] sqls = {
				getCreateSql(AccessLogType.ACCESS, thisMonth.getTime()),
				getCreateSql(AccessLogType.REGISTER, thisMonth.getTime()),
				getCreateSql(AccessLogType.LOGIN, thisMonth.getTime()),
				getCreateSql(AccessLogType.UPDATE, thisMonth.getTime()),
				getCreateSql(AccessLogType.FIND, thisMonth.getTime()),
				
				getCreateSql(AccessLogType.REGISTER, nextMonth.getTime()),
				getCreateSql(AccessLogType.LOGIN, nextMonth.getTime()),
				getCreateSql(AccessLogType.UPDATE, nextMonth.getTime()),
				getCreateSql(AccessLogType.ACCESS, nextMonth.getTime()),
				getCreateSql(AccessLogType.FIND, nextMonth.getTime())};
		try {
			jdbcTemplate.batchUpdate(sqls);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}	
	
	private String getCreateSql(byte type, Date date) {
		String sql = "CREATE TABLE if not exists "
				+ getTableName(type, date)
				+ " ("
				+ "`id` INT(10) NOT NULL AUTO_INCREMENT,"
				+ "`uid` INT(10) NOT NULL,"
				+ "`fromid` INT(10) NOT NULL,"
				+ "`ip` VARCHAR(20) NOT NULL DEFAULT '',"
				+ "`mobile` VARCHAR(50) NULL DEFAULT '',"
				+ "`access_time` DATETIME NOT NULL DEFAULT '1900-01-01 00:00:00',"
				+ "`field1` VARCHAR(50) NULL DEFAULT '', "
				+ "`field2` VARCHAR(50) NULL DEFAULT '',"
				+ "`field3` VARCHAR(50) NULL DEFAULT '',"
				+ "PRIMARY KEY (`id`),"
				+ "INDEX `access_uid_fromid_mobile` (`uid`, `fromid`, `mobile`)"
				+ ")" + " COLLATE='utf8_general_ci' " + " ENGINE=MyISAM;";
		return sql;
	}
	
	private String getTableName(byte type, Date date) {
		String tableName = monthFormat.format(date);
		switch (type) {
		case AccessLogType.REGISTER:
			tableName = "`register_log_" + tableName + "`";
			break;
		case AccessLogType.LOGIN:
			tableName = "`login_log_" + tableName + "`";
			break;
		case AccessLogType.UPDATE:
			tableName = "`update_log_" + tableName + "`";
			break;
		case AccessLogType.FIND:
			tableName = "`find_log_" + tableName + "`";
			break;
		default:
			tableName = "`access_log_" + tableName + "`";
			break;
		}
		return tableName;
	}

	public void add(Serializable obj) {
		final AccessLog log = (AccessLog) obj;
		if (log.getAccessTime() == null) {
			log.setAccessTime(new Date());
		}
		
		AccessLog[] logs = null;
		int length = 0;
		
		synchronized (LOCK) {
			array[arraySize++] = log;
			
			if (arraySize >= BATCH_SIZE) {
				//array = new AccessLog[BATCH_SIZE]
				logs = array;
				// arraySize = 0
				length = arraySize;
				array = new AccessLog[BATCH_SIZE];
				arraySize = 0;
			}
		}
		
		lastLogTime = System.currentTimeMillis();
		
		if (null != logs && length > 0) {
			Arrays.sort(logs, MyLogComparator);
			System.out.println("log batch ... length=" + length);
			log(logs, length);
		}
	}

	private void log(final AccessLog[] logArray, final int length) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(statDataSource);
				byte type = -1;
				String sql = null;
				List<Object[]> batchArgs = null;
				for (int i = 0; i < length && i < logArray.length; i++) {
					AccessLog log = logArray[i];
					if (log.getType() != type) {
						type = log.getType();
						if (sql != null && batchArgs != null) {
							simpleJdbcTemplate.batchUpdate(sql, batchArgs);
						}
						sql = "insert into "
								+ getTableName(log.getType(),
										log.getAccessTime())
								+ " "
								+ "(uid,fromid,ip,mobile,access_time,field1,field2,field3) "
								+ "values (?,?,?,?,?,?,?,?)";
						batchArgs = new ArrayList<Object[]>();
					}

					Object[] arg = { log.getUid(), log.getFromid(),
							log.getIp(), log.getMobile(), log.getAccessTime(),
							log.getField1(), log.getField2(), log.getField3() };
					batchArgs.add(arg);
				}
				if (sql != null && batchArgs != null) {
					simpleJdbcTemplate.batchUpdate(sql, batchArgs);
				}
			}
		});
	}
}
