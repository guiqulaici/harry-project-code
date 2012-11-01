<?xml version="1.0" encoding="UTF-8"?> 
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd"> 
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>
<html xmlns="http://www.w3.org/1999/xhtml">  
<%
String version = request.getParameter("version");            //获取神州付消费接口的版本号
String merId = request.getParameter("merId");        //获取商户ID
String payMoney = request.getParameter("payMoney");    //获取消费金额
String cardMoney = request.getParameter("cardMoney");            //神州付卡面额
String orderId = request.getParameter("orderId");        //获取商户订单号
String payResult = request.getParameter("payResult");    //获取交易结果,1 成功 0 失败
String privateField = request.getParameter("privateField");        //获取商户私有数据
String payDetails = request.getParameter("payDetails"); //获取消费详情
String returnMd5String = request.getParameter("md5String");        //获取MD5加密串
String errcode = request.getParameter("errcode");
String signString = request.getParameter("signString");            //神州付证书签名
String privateKey = ""; // 找神州付拿
String combineString = version + "|" + merId + "|" + payMoney + "|" + cardMoney + "|" + orderId + "|" + payResult + "|" + privateField + "|" + payDetails + "|" + privateKey;
String md5String = DigestUtils.md5Hex(combineString);

if (md5String.equals(returnMd5String)) {
	System.out.println("md5验证成功!!");
	if ("1".equals(payResult)) {
		System.out.println("充值成功!!");
	} else {
		System.out.println("充值失败!!");
	}
} else {
	System.out.println("md5验证失败!!");
}

response.getWriter().write(orderId);
%>
<head><title>神州付返回页面</title>
</head>
<body>
</body>
</html>


 

 