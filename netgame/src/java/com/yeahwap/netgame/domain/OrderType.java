package com.yeahwap.netgame.domain;

public class OrderType {

	public static final byte WAITPAY = 1;
	public static final byte PASSPAY = 2;
	// 订单调用支付连接时失败
	public static final byte CLOSEPAY = 3;
}
