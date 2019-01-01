package com.jpmorgan.tradehandler.utils;

public class ThreadUtils {
	
	public static void sleepForAWhile() throws InterruptedException {
		long sleepingTime = (long)(Math.random() * 1000);
		Thread.sleep(sleepingTime);
	}
}
