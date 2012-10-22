package com.yeahwap.netgame.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Harry
 * 
 */
public class HttpUtil {
	public static String getRemoteIP(HttpServletRequest req) {
		return req.getHeader("X-Real-IP") == null ? "127.0.0.1" : req.getHeader("X-Real-IP");
	}
}
