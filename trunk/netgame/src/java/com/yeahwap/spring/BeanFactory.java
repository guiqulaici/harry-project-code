package com.yeahwap.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created on 2012-10-10 14:27:03
 * 
 * @author harry
 *         <ul>
 *         <li>Title: BeanFactory.java</li>
 *         <li>description: TODO</li>
 *         <ul>
 */
public class BeanFactory {
	private static final Logger log = Logger.getLogger(BeanFactory.class);
	private static final ApplicationContext ctx = getContext();

	private static ApplicationContext getContext() {
		ApplicationContext ctx = null;
		try {
			ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
		} catch (BeansException e) {
			log.error("BeanFactory initial error !!", e);
		}

		return ctx;
	}

	public static Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}
	
}
