package com.test.thread;

public class testThread {
	public static void main(String[] args) {
		RunnaleNew();
	}

	private static void threadNew() {
		while (true) {
			try {
				Thread.sleep(1000 * 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Thread t = new Thread() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
					System.out.println("this is NO.1 thread!!");
				}
			};
			t.setDaemon(true);
			t.start();
		}
	}

	private static void RunnaleNew() {
		while (true) {
			try {
				Thread.sleep(1000 * 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
					System.out.println("this is NO.1 runnable!!");
				}
			});

			t.setDaemon(true);
			t.start();
		}
	}
}
