<%@page import="com.yeahwap.netgame.service.UserService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!
WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
UserService us = (UserService)wac.getBean("userService");
%>
<%
out.println(us.get(1).getName());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>

</body>
</html>