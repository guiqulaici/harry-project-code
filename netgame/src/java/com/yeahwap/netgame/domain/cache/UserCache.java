package com.yeahwap.netgame.domain.cache;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.yeahwap.netgame.domain.pojo.User;

@Component
@Aspect
public class UserCache {
	private static final Logger log = Logger.getLogger("UserCache.cass");
	private static final int DEFAULT_EXPIRE = 60 * 30;
	private static final String DEFAULT_PREFIX = "user_";
	
	@Around(value="execution(* com.yeahwap.netgame.service.UserService.add(com.yeahwap.netgame.domain.pojo.User)) && args(u)", argNames="u")
	private Object addCache(ProceedingJoinPoint pjp , User u) throws Throwable{
		// key = DEFAULT_PREFIX + ""
		// User user = MemcacheUtil.get();
		
		return pjp.proceed();
	}
}
