package com.yeahwap.netgame.domain.cache;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.yeahwap.netgame.domain.pojo.User;
import com.yeahwap.netgame.util.MemcacheUtil;

/**
 * Create on 2012-10-14 23:42
 * 
 * @author Harry
 *         <ul>
 *         <li></li>
 *         <li></li>
 *         </ul>
 * 
 */
@Component
@Aspect
public class UserCache {
	private static final Logger log = Logger.getLogger("UserCache.cass");
	private static final int DEFAULT_EXPIRE = 60 * 30;
	private static final String DEFAULT_PREFIX = "user_";

	@Around(value = "execution(* com.yeahwap.netgame.service.UserService.add(com.yeahwap.netgame.domain.pojo.User)) && args(u)", argNames = "u")
	private Object addCache(ProceedingJoinPoint pjp, User u) throws Throwable {
		Object ret = pjp.proceed();
		String key = DEFAULT_PREFIX + (Integer) ret;
		MemcacheUtil.delete(key);

		if (log.isDebugEnabled()) {
			System.out.println("delete from memcache:" + key);
			log.debug("delete from memcache:" + key);
		}
		return ret;
	}

	@Around(value = "execution(* com.yeahwap.netgame.service.UserService.update(com.yeahwap.netgame.domain.pojo.User)) && args(u)", argNames = "u")
	private void updateCache(ProceedingJoinPoint pjp, User u) throws Throwable {
		pjp.proceed();
		String key = DEFAULT_PREFIX + u.getId();
		MemcacheUtil.delete(key);

		if (log.isDebugEnabled()) {
			System.out.println("delete from memcache:" + key);
			log.debug("delete from memcache:" + key);
		}

	}

	@Around(value = "execution (* com.yeahwap.netgame.service.UserService.get(int)) && args(id)", argNames = "id")
	private Object getCache(ProceedingJoinPoint pjp, int id) throws Throwable {
		String key = DEFAULT_PREFIX + id;
		Object ret = MemcacheUtil.get(key);

		if (ret == null) {
			ret = pjp.proceed();

			if (null != ret) {
				MemcacheUtil.set(key, DEFAULT_EXPIRE, ret);

				if (log.isDebugEnabled()) {
					log.debug("set to memcache:" + key.toString());
				}
			}
		}

		if (ret != null) {
			log.debug("get from memcache : " + key.toString());
		}

		return ret;
	}

	@Around(value = "execution(* com.yeahwap.netgame.service.UserService.getUserByName(String)) && args(name)", argNames = "")
	private Object getUserByNameCache(ProceedingJoinPoint pjp, String name)
			throws Throwable {
		return pjp.proceed();
	}
}
