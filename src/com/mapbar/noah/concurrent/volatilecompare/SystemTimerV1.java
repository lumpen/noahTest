package com.mapbar.noah.concurrent.volatilecompare;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SystemTimerV1 {
	private final static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	private static final long tickUnit = Long.parseLong(System.getProperty("notify.systimer.tick", "50"));
	private static volatile long time = System.currentTimeMillis();
	
	private static class TimerTicker implements Runnable{
		public void run(){
			time = System.currentTimeMillis();
		}
	}
	
	public static long currentTimeMillis(){
		return time;
	}
	
	static{
		executor.scheduleAtFixedRate(new TimerTicker(), tickUnit, tickUnit, TimeUnit.MILLISECONDS);
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run(){
				executor.shutdown();
			}
		});
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for(int i = 0; i < 1000000000; i++){
			SystemTimerV1.currentTimeMillis();
		}
		long end = System.currentTimeMillis();
		System.out.println("v1: " + (end-start));
	}
}
