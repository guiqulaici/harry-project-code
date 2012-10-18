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
 * 
 */
@Component
@Aspect
public class UserCache {
	private static final Logger log = Logger.getLogger("UserCache.class");
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
		// 删除所有当前ID的用户的所有memecahed
		pjp.proceed();
		String key = DEFAULT_PREFIX + u.getId();
		MemcacheUtil.delete(key);

		if (log.isDebugEnabled()) {
			System.out.println("delete from memcache:" + key);
			log.debug("delete from memcache:" + key);
		}

	}
}
