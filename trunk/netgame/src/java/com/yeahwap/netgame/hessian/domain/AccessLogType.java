package com.yeahwap.netgame.hessian.domain;

/**
 * Create on 2012-10-22 16:04
 * 
 * @author Harry
 *         <ul>
 *         <li>Title: AccessLogType.java</li>
 *         <li>description: 日志数据库区分表状态</li>
 *         <li></li>
 *         </ul>
 */
public class AccessLogType {
	public static final byte ACCESS = 0; 
	public static final byte REGISTER = 1;
	public static final byte LOGIN = 2;
	public static final byte UPDATE = 3;
	public static final byte FIND = 4;
}
