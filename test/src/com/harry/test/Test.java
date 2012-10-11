package com.harry.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;



public class Test {
	//private final static Logger REVENUEDATELOG = Logger.getLogger("revenueDataLogHarry");
	// private final static Logger log = Logger.getLogger(Test.class);
	
	public static void main(String[] args) {
		System.out.println(("123").indexOf("abc")); 
//		REVENUEDATELOG.info("this revenueDateLog!!!");
//		// log.info("this is log!!");
//		System.out.println("log!!");
//		正则是一个字符一个字符匹配的
		p(("abc").matches("...")); 
		
		p(("123").replaceAll("\\d", "~"));
		
		Pattern pattern = Pattern.compile("[a-z]{3}");
		
		Matcher m = pattern.matcher("abc");
		System.out.println(m.matches());
	}
	
	private static void p(Object o) {
		System.out.println(o);
	}
}
