package com.yeahwap.netgame.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
		"yyyy-MM-dd");
	private static SimpleDateFormat GMT_FORMAT = new SimpleDateFormat(
		"EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);

	static {
		GMT_FORMAT.setTimeZone(TimeZone.getTimeZone(""));
	}

	public static String getGMTString(Date date) {
		return GMT_FORMAT.format(date);
	}

	public static boolean isUsePattern(String dateString, String pattern) {
		if (dateString == null) {
			throw new NullPointerException();
		}

		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			formatter.parse(dateString);
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	public static String format(Date date, String pattern) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	public static Date parse(String dateString, String pattern) {
		if (dateString == null || dateString.trim().length() == 0) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			return formatter.parse(dateString);
		} catch (ParseException ex) {
			throw new IllegalArgumentException("Illegal datetime string "
					+ dateString);
		}
	}

	public static java.sql.Date parseSQLDate(String dateString, String pattern) {
		Date utilDate = parse(dateString, pattern);
		return (utilDate != null) ? (new java.sql.Date(utilDate.getTime()))
				: null;
	}

	public static Date nextDate(Date now) {
		if (now == null) {
			throw new NullPointerException();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	public static int getYearOfDate(Date date) {
		if (date == null) {
			return 1990;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int getMonthOfDate(Date date) {
		if (date == null) {
			return 1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getDayOfDate(Date date) {
		if (date == null) {
			return 1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static Date parse(String str) {
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date;
	}

	public static String format(Date date) {
		return dateFormat.format(date);
	}

}