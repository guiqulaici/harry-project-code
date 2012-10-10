package com.test.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;



public class Test {
	private static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static void get() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("this is test!!!");
				System.out.println(dayFormat.format(new Date()));
			}
		};
		
		Timer timer = new Timer();
		
		try {
			timer.schedule(task, dayFormat.parse("2012-7-20 15:04:00"), 1000*60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		get();
	}
}
