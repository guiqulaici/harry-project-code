package com.harry.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;



public class Test {
	//private final static Logger REVENUEDATELOG = Logger.getLogger("revenueDataLogHarry");
	// private final static Logger log = Logger.getLogger(Test.class);
	
	public static void main(String[] args) {
		String url = "xxxx/sss/aaaa";
		System.out.println(url.substring(url.lastIndexOf("/")));
	}
	
	private static void p(Object o) {
		System.out.println(o);
	}
}
