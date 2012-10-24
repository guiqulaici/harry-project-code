<?xml version="1.0" encoding="UTF-8"?> 
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd"> 
<%@ page language="java" pageEncoding="UTF-8" contentType="application/vnd.wap.xhtml+xml; charset=utf-8" %>
<%
// 自动解析PC web请求还是mobilephone web请求
String acceptHeader = request.getHeader("accept");
if (acceptHeader.indexOf("application/vnd.wap.xhtml+xml") != -1){
	response.setContentType("application/vnd.wap.xhtml+xml");
}else if (acceptHeader.indexOf("application/xhtml+xml") != -1) {
	response.setContentType("application/xhtml+xml");
}else {
	response.setContentType("text/html");
}
%>
<html xmlns="http://www.w3.org/1999/xhtml">  
<head>
	<title>手机支付</title>
</head>
<body>
<!--支付接口: http://pay3.shenzhoufu.com/interface/version3/serverconnszx/entry-noxml.aspx -->

</body>
</html>


 

 