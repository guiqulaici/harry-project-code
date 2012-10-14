package com.yeahwap.netgame.domain.cache;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.yeahwap.netgame.util.MemcacheUtil;
/**
 * 
 * @author Harry
 *
 */
@Component
@Aspect
public class OperatorCache {
	private static final Logger log = Logger.getLogger(OperatorCache.class);
	private static final int DEFAULT_EXPIRE = 60 * 30;
	private static final String DEFAULT_PREFIX = "operator_";
	
	@Around(value="execution(* com.yeahwap.netgame.service.OperatorService.get(int)) && args(id)", argNames="id")
	public Object getCache(ProceedingJoinPoint pjp, int id) throws Throwable {
		String key = DEFAULT_PREFIX + id;
		Object ret = MemcacheUtil.get(key);
		
		if (ret != null && log.isDebugEnabled()) {
			log.debug("get from memcache:" + key.toString());
		}
		
		if (ret == null) {
			ret = pjp.proceed();
			
			if (null != ret) {
				MemcacheUtil.set(key.toString(), DEFAULT_EXPIRE, ret);
				if (log.isDebugEnabled()) {
					log.debug("set to memcache:" + key.toString());
				}
			}
		}
		
		return ret;
	}
}
