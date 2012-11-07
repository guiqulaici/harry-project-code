<?xml version="1.0" encoding="UTF-8"?> 
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd"> 
<%@page import="com.yeahwap.netgame.service.SzfTransactional"%>
<%@page import="com.yeahwap.netgame.domain.pojo.Order"%>
<%@page import="com.yeahwap.netgame.domain.OrderStatus"%>
<%@page import="com.yeahwap.netgame.util.StringUtil"%>
<%@page import="com.yeahwap.netgame.domain.pojo.SzfOrder"%>
<%@page import="com.yeahwap.netgame.service.OrderService"%>
<%@page import="com.yeahwap.netgame.service.SzfOrderService"%>
<%@page import="com.yeahwap.netgame.util.BeanFactory"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>
<html xmlns="http://www.w3.org/1999/xhtml">  

<%
SzfOrderService szfOrderService = (SzfOrderService)BeanFactory.getBean("szfOrderService", this.getServletContext());
OrderService orderService = (OrderService)BeanFactory.getBean("orderService", this.getServletContext());
SzfTransactional szfTransactional = (SzfTransactional)BeanFactory.getBean("szfTransactional", this.getServletContext());
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
String privateKey = "Harry_admin_szf"; // 找神州付拿
String combineString = version + "|" + merId + "|" + payMoney + "|" + cardMoney + "|" + orderId + "|" + payResult + "|" + privateField + "|" + payDetails + "|" + privateKey;
String md5String = DigestUtils.md5Hex(combineString);

System.out.println("returnData:" + version + ";" + merId + ";" + payMoney + ";" + cardMoney + ";" + orderId + ";" + payResult + ";" + privateField + ";" + payDetails + ";" + md5String + ";" + errcode);

// 查看当前账单的状态,并做好orderId为空的处理
if (orderId == null || ("").equals(orderId)) {
	return;
}

String orderIdStr = orderId.substring(orderId.lastIndexOf("-") + 1, orderId.length());
int accountId = StringUtil.getInt(orderIdStr, 0); 
SzfOrder szfOrder = szfOrderService.get(orderId);
Order order = orderService.get(accountId);

if (md5String.equals(returnMd5String)) {
	System.out.println("md5 vaild success!!");
	if ("1".equals(payResult)) {
		System.out.println("pay success !!");
		szfTransactional.updateAccountStatus(szfOrder, order, OrderStatus.PASSPAY, privateField);
		try {response.getWriter().write(orderId);}catch(Exception ex){System.out.println(ex.getMessage());}
	} else {
		// 记录支付失败日志
		szfTransactional.updateAccountStatus(szfOrder, order, OrderStatus.PAYERROR, privateField);
		System.out.println("pay error !!");
	}
} else {
	// 验证失败
	szfTransactional.updateAccountStatus(szfOrder, order, OrderStatus.VALIDERROR, privateField);
	System.out.println("md5 valid error !!");
}
%>
<head><title>神州付返回页面</title>
</head>
<body>
</body>
</html>


 

 