<%@page import="com.yeahwap.netgame.hessian.UserHessianService"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
	ApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext-hibernate.xml");

	UserHessianService uhs = (UserHessianService)cxt.getBean("userHessianService");
	
	uhs.add("xxx");
%>
