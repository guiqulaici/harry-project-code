package com.yeahwap.netgame.domain;

public class OrderStatus {
	// 等待支付
	public static final byte WAITPAY = 1;
	// 支付成功
	public static final byte PASSPAY = 2;
	// 订单调用支付连接时失败
	public static final byte CLOSEPAY = 3;
	// 订单支付失败
	public static final byte PAYERROR = 4;
	// 订单验证失败
	public static final byte VALIDERROR = 5;
}
