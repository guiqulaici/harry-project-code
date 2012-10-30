<?xml version="1.0" encoding="UTF-8"?> 
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd"> 
<%@ page language="java" pageEncoding="UTF-8" contentType="application/vnd.wap.xhtml+xml; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="sf"%>
<html xmlns="http://www.w3.org/1999/xhtml">  
<head><title>联通卡支付</title>
</head>
<c:url value="/szf/${fromid}/${uid}/payDianXin.do" var="dianxinzf"></c:url>
<body>
<sf:form action="${dianxinzf}" method="post" modelAttribute="szfcard">
	<sf:hidden path="cardType" />
	<table>
		<tr><td>移动卡序列号:</td><td><sf:input path="cardSN"/><sf:errors path="cardSN"/></td></tr>
		<tr>
		<td>移动卡面值:</td>
		<td>
			<sf:radiobutton path="cardMoney" value="50"/>50元
			<sf:radiobutton path="cardMoney" value="100"/>100元
			<sf:radiobutton path="cardMoney" value="300"/>300元
			<sf:radiobutton path="cardMoney" value="500"/>500元
            <span>请用户确认充值卡面值与产品面值相符，否则后果自负</span>
        </td>
		</tr>
		<tr><td>移动卡密码:</td><td><sf:password path="cardPassword"/><sf:errors path="cardPassword"/></td></tr>
		<tr><td><input type="submit" value="确认支付"/></td></tr>
	</table>
</sf:form>
</body>
</html>
 

 