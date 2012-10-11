package com.harry.test;

import java.util.Calendar;
import java.util.Date;

public class CalTest {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 1);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		System.out.println(cal.getTime());
	}
}
