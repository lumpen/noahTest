package com.mapbar.noah.threadtest;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * 
 * @author lizn
 * 一次的结果
 * Capacity: 1
Pairs: 1	Throughput : 7565 ns/item	Throughput : 7443 ns/item
Pairs: 2	Throughput : 8096 ns/item	Throughput : 8143 ns/item
Pairs: 4	Throughput : 8101 ns/item	Throughput : 8144 ns/item
Pairs: 8	Throughput : 8055 ns/item	Throughput : 8059 ns/item
Pairs: 16	Throughput : 7760 ns/item	Throughput : 7646 ns/item
Pairs: 32	Throughput : 7709 ns/item	Throughput : 7671 ns/item
Pairs: 64	Throughput : 7850 ns/item	Throughput : 7818 ns/item
Pairs: 128	Throughput : 8223 ns/item	Throughput : 8104 ns/item
Capacity: 10
Pairs: 1	Throughput : 626 ns/item	Throughput : 655 ns/item
Pairs: 2	Throughput : 406 ns/item	Throughput : 434 ns/item
Pairs: 4	Throughput : 491 ns/item	Throughput : 468 ns/item
Pairs: 8	Throughput : 519 ns/item	Throughput : 516 ns/item
Pairs: 16	Throughput : 490 ns/item	Throughput : 490 ns/item
Pairs: 32	Throughput : 531 ns/item	Throughput : 641 ns/item
Pairs: 64	Throughput : 511 ns/item	Throughput : 525 ns/item
Pairs: 128	Throughput : 550 ns/item	Throughput : 580 ns/item
Capacity: 100
Pairs: 1	Throughput : 608 ns/item	Throughput : 610 ns/item
Pairs: 2	Throughput : 449 ns/item	Throughput : 415 ns/item
Pairs: 4	Throughput : 454 ns/item	Throughput : 447 ns/item
Pairs: 8	Throughput : 438 ns/item	Throughput : 468 ns/item
Pairs: 16	Throughput : 450 ns/item	Throughput : 446 ns/item
Pairs: 32	Throughput : 452 ns/item	Throughput : 459 ns/item
Pairs: 64	Throughput : 480 ns/item	Throughput : 465 ns/item
Pairs: 128	Throughput : 475 ns/item	Throughput : 491 ns/item
Capacity: 1000
Pairs: 1	Throughput : 628 ns/item	Throughput : 616 ns/item
Pairs: 2	Throughput : 430 ns/item	Throughput : 414 ns/item
Pairs: 4	Throughput : 455 ns/item	Throughput : 441 ns/item
Pairs: 8	Throughput : 450 ns/item	Throughput : 437 ns/item
Pairs: 16	Throughput : 452 ns/item	Throughput : 465 ns/item
Pairs: 32	Throughput : 449 ns/item	Throughput : 475 ns/item
Pairs: 64	Throughput : 441 ns/item	Throughput : 452 ns/item
Pairs: 128	Throughput : 435 ns/item	Throughput : 431 ns/item
 */
public class PutTakeTestAddTimer extends TestCase {
	
	private static final ExecutorService pool = Executors.newCachedThreadPool();
	private final AtomicInteger putSum = new AtomicInteger(0);
	private final AtomicInteger takeSum = new AtomicInteger(0);
	private final CyclicBarrier barrier;
	private final BoundedBuffer<Integer> bb;
	private final int nTrials, nPairs;
	private final BarrierTimer timer;
	
	public static void main(String[] args) throws Exception{
		int tpt = 100000;//每个线程尝试的次数
		for(int cap = 1; cap <= 1000; cap *= 10){
			System.out.println("Capacity: " + cap);
			for(int pairs = 1; pairs <= 128; pairs *=2){
				PutTakeTestAddTimer t = new PutTakeTestAddTimer(cap, pairs, tpt);
				System.out.print("Pairs: " + pairs + "\t");
				t.test();
				System.out.print("\t");
				Thread.sleep(1000);
				t.test();
				System.out.println();
				Thread.sleep(1000);
			}
		}
		pool.shutdown();
	}
	
	public PutTakeTestAddTimer(int capacity, int npairs, int ntrials){
		this.bb = new BoundedBuffer<Integer>(capacity);
		this.nTrials = ntrials;
		this.nPairs = npairs;
		this.timer = new BarrierTimer();
		this.barrier = new CyclicBarrier(npairs * 2 + 1, timer);
	}

	@Test
	void test(){
		try{
			timer.clear();
			for(int i = 0; i < nPairs; i++){
				pool.execute(new Producer());
				pool.execute(new Consumer());
			}
			barrier.await();//等待所有线程做好准备
			barrier.await();//等待所有线程最终完成
			long nsPerItem = timer.getTime() / (nPairs * (long) nTrials);
			System.out.print("Throughput : " + nsPerItem + " ns/item");
			assertEquals(putSum.get(), takeSum.get());
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
	
	class BarrierTimer implements Runnable{
		private boolean started;
		private long startTime, endTime;
		
		public synchronized void run(){
			long t = System.nanoTime();
			if(!started){
				started = true;
				startTime = t;
			}else
				endTime = t;
		}
		
		public synchronized void clear(){
			started = false;
		}
		
		public synchronized long getTime(){
			return endTime - startTime;
		}
	}
	
	static int xorShift(int y){
		y ^= (y << 6);
		y ^= (y >>> 21);
		y ^= (y << 7);
		return y;
	}
}
