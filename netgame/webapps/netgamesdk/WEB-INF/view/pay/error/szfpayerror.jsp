<?xml version="1.0" encoding="UTF-8"?> 
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd"> 
<%@ page language="java" pageEncoding="UTF-8" contentType="application/vnd.wap.xhtml+xml; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">  
<head><title>支付出错页面</title>
<c:url value="/pay/pay.do?uid=${uid}&amp;fromid=${fromid}" var="payurl"></c:url>
</head>
<body>
	支付失败...<a href="${payurl}">请返回</a>....
</body>
</html>

