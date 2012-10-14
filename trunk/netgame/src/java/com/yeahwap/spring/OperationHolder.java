package com.yeahwap.spring;
/**
 * 
 * @author Harry
 *
 */
public class OperationHolder {

	public static String READONLY = "readOnly";
	public static String WRITABLE = "writable";

	private static final ThreadLocal<String> holder = new ThreadLocal<String>();

	public static void setOperation(String op) {
		holder.set(op);
	}

	public static String getOperation() {
		return holder.get();
	}
	
	public static void remove() {
		holder.remove();
	}
}
