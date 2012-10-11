package com.mapbar.noah.concurrent.volatilecompare;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class SystemTimerV3 {
	private final static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	private static final long tickUnit = Long.parseLong(System.getProperty("notify.systimer.tick", "50"));
	private static AtomicLong time = new AtomicLong(System.currentTimeMillis());
	
	private static class TimerTicker implements Runnable{
		public void run(){
			time.set(System.currentTimeMillis());
		}
	}
	
	public static long currentTimeMillis(){
		return time.get();
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
			SystemTimerV3.currentTimeMillis();
		}
		long end = System.currentTimeMillis();
		System.out.println("v3: " + (end-start));
	}
}
