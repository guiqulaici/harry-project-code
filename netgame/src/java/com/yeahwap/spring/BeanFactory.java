package com.yeahwap.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;


/**
 * Create on 2012-10-19 0:45
 * 
 * @author Harry
 * 
 * <ul>
 * <li>Title: BeanFactory.java</li>
 * <li>description: BeanFactory.java初始化application-hibernate.xml容器</li>
 * </ul>
 * 
 */
public class BeanFactory {
	private static final Logger log = LoggerFactory.getLogger(BeanFactory.class);
	private static ApplicationContext cxt = null;
	
	synchronized public static void init()  {
		
	}
}
