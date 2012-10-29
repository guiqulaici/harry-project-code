package com.harry.test;




public class Test {
	public static void main(String[] args) {
		String s = "20121029-10203-10001";
		String orderIdStr = s.substring(s.lastIndexOf("-") + 1, s.length());
		System.out.println(orderIdStr);
	}
}
