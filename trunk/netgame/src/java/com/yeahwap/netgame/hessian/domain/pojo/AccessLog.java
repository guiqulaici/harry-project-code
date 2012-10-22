package com.yeahwap.netgame.hessian.domain.pojo;

import java.util.Date;

import com.yeahwap.netgame.hessian.domain.AccessLogType;

/**
 * Create on 2012-10-22 17:18
 * 
 * @author Harry
 *         <ul>
 *         <li>Title: AccessLog.java</li>
 *         <li>description: 用户相关动作的日志的封装,支持远程调用</li>
 *         </ul>
 */

public class AccessLog implements java.io.Serializable {
	private static final long serialVersionUID = 4056846160808496948L;
	
	private int id;
	private int uid; 
	private int fromid;
	private String ip;
	private String mobile;
	private Date accessTime;
	private String field1;
	private String field2;
	private String field3;
	private byte type = AccessLogType.ACCESS;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getFromid() {
		return fromid;
	}

	public void setFromid(int fromid) {
		this.fromid = fromid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

}
