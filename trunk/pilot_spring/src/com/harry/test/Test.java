package com.harry.test;

import java.util.Calendar;
import java.util.Date;

import com.spring.mvc.domain.HArryCard;

public class Test {
	int i = 0;
	HArryCard card = new HArryCard();
	
	public void get() {
		i = 101;
		card.setId(100);
	}
	
	public void get1() {
		System.out.println(card.getId());
		System.out.println(i);
	}
	
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 2);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		Date startTime = cal.getTime();
		System.out.println(startTime);
		
		
		Calendar cal_new = Calendar.getInstance();
		cal_new.setTime(new Date());
		cal_new.add(Calendar.DAY_OF_MONTH, -1);
		cal_new.set(Calendar.HOUR_OF_DAY, 0);
		cal_new.set(Calendar.SECOND, 0);
		cal_new.set(Calendar.MINUTE, 0);
		Date beginTime = cal_new.getTime();
		System.out.println(beginTime);
	}
}
