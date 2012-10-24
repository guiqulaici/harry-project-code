package com.harry.test;

import java.io.UnsupportedEncodingException;



public class Test {
	public static void main(String[] args) {
		String url = "http://www.yeahyoo.com/netgame/sdk/pay.do";
		try {
			System.out.println(java.net.URLEncoder.encode(url, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
