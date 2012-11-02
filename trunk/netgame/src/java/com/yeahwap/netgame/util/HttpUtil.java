package com.yeahwap.netgame.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Harry
 * 
 */
public class HttpUtil {
	public static String getRemoteIP(HttpServletRequest req) {
		String ip = req.getHeader("X-Real-IP");
		
		if (ip == null || ("").equals(ip)) {
			ip = req.getRemoteAddr() == null ? "127.0.0.1" : req.getRemoteAddr();
		}
		
		return ip;
	}
}
