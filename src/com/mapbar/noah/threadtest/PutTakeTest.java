package com.mapbar.noah.threadtest;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import junit.framework.TestCase;

public class PutTakeTest extends TestCase {
	
	private static final ExecutorService pool = Executors.newCachedThreadPool();
	private final AtomicInteger putSum = new AtomicInteger(0);
	private final AtomicInteger takeSum = new AtomicInteger(0);
	private final CyclicBarrier barrier;
	private final BoundedBuffer<Integer> bb;
	private final int nTrials, nPairs;
	
	public static void main(String[] args) {
//		for(int i = 0; i < 10000; i++)
			new PutTakeTest(10, 10, 100000).test();
		pool.shutdown();
	}
	
	public PutTakeTest(int capacity, int npairs, int ntrials){
		this.bb = new BoundedBuffer<Integer>(capacity);
		this.nTrials = ntrials;
		this.nPairs = npairs;
		this.barrier = new CyclicBarrier(npairs * 2 + 1);
	}

	@Test
	void test(){
		try{
			for(int i = 0; i < nPairs; i++){
				pool.execute(new Producer());
				pool.execute(new Consumer());
			}
			barrier.await();//等待所有线程做好准备
			barrier.await();//等待所有线程最终完成
			assertEquals(putSum.get(), takeSum.get());
//			System.out.println(putSum.get() - takeSum.get());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	class Producer implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				int seed = (this.hashCode() ^ (int) System.nanoTime());
				int sum = 0;
				barrier.await();
				for(int i = nTrials; i > 0; --i){
					bb.put(seed);
					sum += seed;
					seed = xorShift(seed);
				}
				putSum.getAndAdd(sum);
				barrier.await();
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
	}
	
	class Consumer implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				barrier.await();
				int sum = 0;
				for(int i = nTrials; i > 0; --i){
					sum += bb.take();
				}
				takeSum.getAndAdd(sum);
				barrier.await();
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
	}
	
	static int xorShift(int y){
		y ^= (y << 6);
		y ^= (y >>> 21);
		y ^= (y << 7);
		return y;
	}
}
