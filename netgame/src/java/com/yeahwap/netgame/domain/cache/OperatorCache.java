package com.yeahwap.netgame.domain.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class OperatorCache {
	@Around(value="execution(* com.yeahwap.netgame.service.OperatorService.get(int)) && args(id)", argNames="id")
	public Object getCache(ProceedingJoinPoint pjp, int id) throws Throwable {
		System.out.println("我是aop的拦截");
		return pjp.proceed();
	}
}
