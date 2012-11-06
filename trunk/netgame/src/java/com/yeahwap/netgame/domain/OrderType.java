package com.yeahwap.netgame.domain;

public class OrderType {
	// 等待支付
	public static final byte WAITPAY = 1;
	// 支付成功
	public static final byte PASSPAY = 2;
	// 订单调用支付连接时失败
	public static final byte CLOSEPAY = 3;
}
