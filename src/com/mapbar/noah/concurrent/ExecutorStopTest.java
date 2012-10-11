package com.mapbar.noah.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池接收的任务都执行完后，再结束
 * @author lizn
 *
 */
public class ExecutorStopTest {
	
	public static void main(String[] args) {
		ExecutorStopTest et = new ExecutorStopTest();
		System.out.println(ExecutorStopTest.exec.getClass().getName());
		et.run();
		et.stop();
	}

	public void run(){
		for(int i = 0; i < taskNum; i++){
			Task task = new Task();
			task.index = i;
			exec.execute(task);
		}
	}
	
	public void stop(){
		System.out.println("shutdown the thread pool");
		exec.shutdown();
	}
	
	int taskNum = 10;
	
	public static final int threadNum = Runtime.getRuntime().availableProcessors() + 1;
	public static final ExecutorService exec = Executors.newFixedThreadPool(threadNum);
}

class Task implements Runnable{

	int index;
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
//			System.out.println(Thread.currentThread().getId());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(index);
	}
	
}