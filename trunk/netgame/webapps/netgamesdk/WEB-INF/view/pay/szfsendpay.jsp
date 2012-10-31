<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="sf"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<c:url value="/image/loading.gif" var="loadURL"></c:url>
<c:url value="/js/jquery-1.4.2.min.js" var="jquery"></c:url>
<c:url value="/szf/ajax/${fromid}/${uid}/${szfCard.cardType}/${szfCard.cardSN}/${szfCard.cardMoney}/${szfCard.cardPassword}/szfpay.do" var="ajaxURL"></c:url>
<c:url value="/szf/ajax/szfpay.do" var="ajaxURLPay"></c:url>
<c:url value="/pay/pay.do?uid=${uid}&amp;fromid=${fromid}" var="rentuURL"></c:url>

<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript">
function tj() {
	$("#loadDiv").css("display","block");
	$("#submitDiv").css("display","none");
	
	$.ajax({
		type: "post",
		data: "",
		url: "${ajaxURL}",
		success: callback
	});
}

function callback(data) {
	$("#loadDiv").css("display","none");
	$("#loadDiv2").append("<img src=\"${loadURL}\" alt=\"loading\"/>订单ID:" + data + "<br/>订单提交验证中，可返回游戏等待结果..." + "<a href=\"${rentuURL}\">返回</a>");
	$("#loadDiv2").css("display","block");
	
	$.ajax({
		type: "post",
		data: "data=" + data,
		url: "${ajaxURLPay}",
		success: callbackPay
	});
}

function callbackPay(data) {
	alert("很遗憾！" + data + "，详情请联系客服。");
}

</script>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>支付页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-cache">
</head>
<body>
<sf:form method="post" modelAttribute="szfCard">
	<table>
		<tr><td colspan="2">
		<c:choose>
			<c:when test="${szfCard.cardType == 0 }">
			神州行充值:一般1-10分钟到账
			</c:when>
			<c:when test="${szfCard.cardType == 1 }">
			联通卡充值:一般1-10分钟到账
			</c:when>
			<c:when test="${szfCard.cardType == 2 }">
			电信卡充值:一般1-10分钟到账
			</c:when>
		</c:choose>
		</td></tr>
		<sf:hidden path="cardType" />
		<tr><td>充值卡卡号:</td><td><sf:textarea rows="1"  path="cardSN" readonly="true"/></td></tr>
		<tr><td>充值卡密码:</td><td><sf:textarea rows="1"  path="cardPassword" readonly="true"/></td></tr>
		<tr><td>充值金额:</td><td><sf:textarea rows="1"  path="cardMoney" readonly="true"/>元</td></tr>
		
	</table>
	<!-- 调用ajax处理前后验证 -->
	<div id="submitDiv"><input type="button" value="确认提交" onclick="tj();"></div>
	<div id="loadDiv" style="display:none;"><img src="${loadURL}" alt="loading"/> 正在提交数据到运营商, 请稍后...</div>
	<div id="loadDiv2" style="display:none;"></div>
</sf:form>
</body>
</html>
 

 