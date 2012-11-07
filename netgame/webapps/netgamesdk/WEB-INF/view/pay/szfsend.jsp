<?xml version="1.0" encoding="UTF-8"?> 
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd"> 
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="sf"%>
<html xmlns="http://www.w3.org/1999/xhtml">  
<head><title>支付页面</title>
</head>
<c:url value="/szf/${fromid}/${uid}/szfValid.do" var="validUrl"></c:url>
<body>
<sf:form action="${validUrl}" method="post" modelAttribute="szfCard">
	<sf:hidden path="cardType" />
	<table>
		<tr><td>充值卡号:</td><td><sf:input path="cardSN"/><sf:errors path="cardSN"/></td></tr>
		<tr><td>充值卡密码:</td><td><sf:input path="cardPassword"/><sf:errors path="cardPassword"/></td></tr>
		<tr>
		<td>充值卡面额:</td>
		<td>
			<c:if test="${szfCard.cardType == 0}">
				<sf:radiobutton path="cardMoney" value="10"/>10元
			</c:if>
			
			<sf:radiobutton path="cardMoney" value="20"/>20元
			<sf:radiobutton path="cardMoney" value="30"/>30元
			<sf:radiobutton path="cardMoney" value="50"/>50元
			<sf:radiobutton path="cardMoney" value="100"/>100元
			<c:if test="${szfCard.cardType != 2 }">
				<sf:radiobutton path="cardMoney" value="300"/>300元
				<sf:radiobutton path="cardMoney" value="500"/>500元
			</c:if>
        </td>
		</tr>
		<tr><td colspan="2">请用户确认充值卡面值与产品面值相符，否则后果自负</td></tr>
		<tr><td><input type="submit" value="提交"/></td></tr>
	</table>
</sf:form>
</body>
</html>
 

 