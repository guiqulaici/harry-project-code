package com.harry.test;

public class Test {
	private static final String[][] ips = {{"3395419552", "3395419583",	"黑龙江大庆联通"},{"3395534848", "3395536383", "山西太原联通"}}; 
	
	private static long ipToLong(String strIp) {
		long[] ip = new long[4];
		int index1 = strIp.indexOf(".");
		int index2 = strIp.indexOf(".", index1 + 1);
		int index3 = strIp.indexOf(".", index2 + 1);
		
		ip[0] = Long.parseLong(strIp.substring(0, index1));
		ip[1] = Long.parseLong(strIp.substring(index1 + 1, index2));
		ip[2] = Long.parseLong(strIp.substring(index2 + 1, index3));
		ip[3] = Long.parseLong(strIp.substring(index3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}
	
	public static String getAddress(String ip){
		long ipLong = ipToLong(ip);
		System.out.println(ipLong);
		
		for (int i = 0; i < ips.length; i++) {
			if (ipLong >= Long.valueOf(ips[i][0]) && ipLong <=  Long.valueOf(ips[i][1])) {
				return ips[i][2];
			}
		}
		
		return "";
	}
	
	public static void main(String[] args) {
	
	}
}
