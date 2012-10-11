package com.mapbar.noah.concurrent.volatilecompare;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class SystemTimerV4 {
	private final static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	private static final long tickUnit = Long.parseLong(System.getProperty("notify.systimer.tick", "50"));
	private static long time = System.currentTimeMillis();
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private static WriteLock wl = lock.writeLock();
	private static ReadLock rl = lock.readLock();
	private static class TimerTicker implements Runnable{
		public void run(){
			wl.lock();
			try{
				time = System.currentTimeMillis();	
			}finally{
				wl.unlock();	
			}
			
		}
	}
	
	public static long currentTimeMillis(){
		rl.lock();
		try{
			return time;
		}finally{
			rl.unlock();
		}
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
			SystemTimerV4.currentTimeMillis();
		}
		long end = System.currentTimeMillis();
		System.out.println("v4: " + (end-start));
	}
}
