package com.yeahwap.netgame.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Harry
 * 
 */
public class StringUtil {

	final static String regex = "-?\\+?[0-9]{11,13}";
	final static java.util.regex.Pattern pattern = java.util.regex.Pattern
			.compile(regex);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static boolean getBoolean(String getValue, boolean defaultValue) {
		boolean i = defaultValue;
		try {
			i = Boolean.parseBoolean(getValue);
		} catch (Exception e) {
		}
		return i;
	}

	public static byte getByte(String getValue, byte defaultValue) {
		byte i = defaultValue;
		try {
			i = Byte.parseByte(getValue);
		} catch (Exception e) {
		}
		return i;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static Date parseDate(String str) {
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date;
	}

	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

	public static boolean isMobile(String mobile) {
		return mobile != null ? pattern.matcher(mobile).matches() : false;
	}

	public static String tidyMobile(String mobile) {
		return mobile != null ? mobile.replaceAll("^-?\\+?86", "") : null;
	}

	public static int getInt(String getValue, int defaultValue) {
		int i = defaultValue;
		try {
			i = Integer.parseInt(getValue);
		} catch (Exception e) {
		}
		return i;
	}

	public static long getLong(String getValue, long defaultValue) {
		long i = defaultValue;
		try {
			i = Long.parseLong(getValue);
		} catch (Exception e) {
		}
		return i;
	}

	public static float getFloat(String getValue, float defaultValue) {
		float i = defaultValue;
		try {
			i = Float.parseFloat(getValue);
		} catch (Exception e) {
		}
		return i;
	}

	public static String trimLf(String str) {
		String s = str;
		s = s.replace("\r\n", "");
		s = s.replace("\r", "");
		s = s.replace("\n", "");
		s = s.replace("\t", "");
		return s;
	}

	public static String trimDblSpace(String str) {
		String strOutput = str;
		while (strOutput.indexOf("  ") > 0) {
			strOutput = strOutput.replace("  ", " ");
		}
		return strOutput;
	}

	public static String trimTab(String str) {
		return str.replace("\t", "");
	}

	public static String toWml(String str) {
		String s = str;
		s = s.replace("'", "&apos;");
		s = s.replace("\"", "&quot;");
		s = s.replace(">", "&gt;");
		s = s.replace("<", "&lt;");
		s = s.replace("&amp;", "&");
		s = s.replace("&", "amp;");
		return s;
	}

	public static String handleNull(String str) {
		if (str != null
				&& ("NULL".equalsIgnoreCase(str.trim()) || ""
						.equals(str.trim()))) {
			return null;
		} else {
			return str;
		}
	}

}
