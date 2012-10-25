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

// 之前已经验证过了，现在不在验证
String uid = request.getParameter("uid");
String fromid = request.getParameter("fromid");
%>

<html xmlns="http://www.w3.org/1999/xhtml">  
<head><title>手机支付页面</title>
</head>
<body>
请选择您需要支付的方式:
	<table>
		<tr><td><a href="<%=request.getContextPath()%>/pay/szf/yidong.do?uid=<%=uid%>&fromid=<%=fromid%>">移动充值卡</a></td></tr>
		<tr><td><a href="<%=request.getContextPath()%>/pay/szf/liantong.do?uid=<%=uid%>&fromid=<%=fromid%>">联通充值卡</a></td></tr>
		<tr><td><a href="<%=request.getContextPath()%>/pay/szf/dianxin.do?uid=<%=uid%>&fromid=<%=fromid%>">电信充值卡</a></td></tr>
		<tr><td><a href="">银行卡</a></td></tr>
	</table>
</body>
</html>


 

 