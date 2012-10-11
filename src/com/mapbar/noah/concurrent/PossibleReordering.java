package com.mapbar.noah.concurrent;
/**
 * 重排
 * @author lizn
 *
 */
public class PossibleReordering {
	static int x = 0, y = 0;
	static int a = 0, b = 0;
	/**
	 * one的两步赋值语句可能会被重排而导致打印结果不可预期
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException{
		Thread one = new Thread(new Runnable(){
			public void run(){
				a = 1;
				x = b;
			}
		});
		Thread other = new Thread(new Runnable(){
			public void run(){
				b = 1;
				y = a;
			}
		});
		one.start();other.start();
		one.join();other.join();
		System.out.println("(" + x + "," + y + ")");
	}
	
		
}
