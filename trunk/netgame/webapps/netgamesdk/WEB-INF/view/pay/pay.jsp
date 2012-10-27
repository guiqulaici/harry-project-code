<?xml version="1.0" encoding="UTF-8"?> 
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd"> 
<%@ page language="java" pageEncoding="UTF-8" contentType="application/vnd.wap.xhtml+xml; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">  
<head><title>手机支付页面</title>
</head>
<c:url var="yidong" value="/szf/${uid}/${fromid}/yidong.do"></c:url>
<c:url var="liantong" value="/szf/${uid}/${fromid}/liantong.do"></c:url>
<c:url var="dianxin" value="/szf/${uid}/${fromid}/dianxin.do"></c:url>
<c:url var="yinghang" value="/yibao/${uid}/${fromid}/yinghang.do"></c:url>
<c:url var="zhifubao" value="/szf/${uid}/${fromid}/zhifubao.do"></c:url>

<body>
请选择您需要支付的方式:
	<table>
		<tr><td><a href="${yidong}">移动充值卡</a></td></tr>
		<tr><td><a href="${liantong }">联通充值卡</a></td></tr>
		<tr><td><a href="${dianxin }">电信充值卡</a></td></tr>
		<tr><td><a href="${yinghang }">银行卡</a></td></tr>
		<tr><td><a href="${zhifubao }">支付宝</a></td></tr>
	</table>
</body>
</html>


 

 