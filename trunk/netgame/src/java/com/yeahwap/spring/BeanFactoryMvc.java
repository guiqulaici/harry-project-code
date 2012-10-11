package com.yeahwap.spring;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class BeanFactoryMvc{
	public static Object getBean(ServletContext servletContext, String beanName) {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return ctx.getBean(beanName);
	}
}
