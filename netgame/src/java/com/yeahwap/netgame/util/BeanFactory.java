package com.yeahwap.netgame.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * Created on 2012-11-7 14:27:03
 *
 * www.m-time.com
 */

/**
 * @author Harry ye
 * 
 * <ul>
 * <li>Title: BeanFactory.java</li>
 * <li>description: TODO</li>
 * <ul>
 */
public class BeanFactory {
	public static Object getBean(String beanName, ServletContext context) {
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(context);

		if (null == wac) {
			throw new ExceptionInInitializerError(
				"ApplicationContext has not been initialized !! Please invoke init() or setContext(ApplicationContext context) first !!");
		}
		
		return wac.getBean(beanName);
	}
}
