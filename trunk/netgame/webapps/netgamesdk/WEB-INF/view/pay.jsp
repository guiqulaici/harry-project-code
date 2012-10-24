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
<embed type="application/x-unionpayplugin" uc_plugin_id="unionpay" height="53" width="178" 
paydata="ResultURL=http%3A%2F%2F218.80.192.213%3A1725%2Fclsun%2Fresult%3Fid%3D, UseTestMode=true, SpId=0009, SysProvide=00000002, TransCurrency=156, SubmitTime=20120216102552, Type=Purchase.MARsp, OrderId=00002158, MerchantName=商户仿真, Signature=197d29ad957dabef89d13d132af4287c98d1b2c774f370e7761b30d2d03dc0216fbf8aa801b0c4c5e8306f1ba27e4a9c8b1f724f579e072f25a5606105a8d9557a50596d7ef387700250476e83ddb98a4999004c26d3d19ec17c885053ae00748dd8d11602aa5e66f62f98ea6d823a986e6e527c0931f11d277b176dbc358667, ServiceInfo=PA08000#, TerminalId=01042900, TransAmount=000000001201, MerchantId=104290073929901, MerchantCountry=156, OrderContent=鸡蛋">
</embed>
</body>
</html>


 

 