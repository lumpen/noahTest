package com.mapbar.noah.threadtest;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoundedBufferTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
	}
	
	@Test
	public void testTakeBlocksWhenEmpty(){
		final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		Thread taker = new Thread(){
			public void run(){
				try{
					int unused = bb.take();
					fail();
				}catch(InterruptedException success){
//					success.printStackTrace();
					System.out.println("interrput success");
				}
			}
		};
		try{
			taker.start();
			System.out.println("Start     " + System.currentTimeMillis());
			Thread.sleep(LOCKUP_DETECT_TIMEOUT);
			
			System.out.println("Interrupt " + System.currentTimeMillis());
			taker.interrupt();
			System.out.println(taker.isInterrupted());
			
			System.out.println("Join      " + System.currentTimeMillis());
			taker.join(LOCKUP_DETECT_TIMEOUT);
			assertFalse(taker.isAlive());
		}catch(Exception unexcpted){
			fail();
		}
	}
	
	public final static int LOCKUP_DETECT_TIMEOUT = 5000;
	
}
