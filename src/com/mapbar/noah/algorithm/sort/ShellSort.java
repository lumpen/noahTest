package com.mapbar.noah.algorithm.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 希尔排序(按照增量分组进行插入排序)
 * 1 按照数组的长度，选取一个数组下标的增量。通常是d=length/2。
 * 2 遍历数组，按照d进行下一个元素的查找增量，进行插入排序。
 * 3 遍历后，减小d，通常d=d/2，递归步骤2，直到d=1。 
 * @author lizn
 * 测试结果：
 * partition=2的时候，不是最优。4的时候比较好。
 *
 */
public class ShellSort {
	public static int partition = 4;
	/**
	 * 升序
	 * @param noSorted
	 * @return
	 */
	public static int[] ascSort(int[] noSorted){
		int d = noSorted.length/partition;
		while(d > 1){
			noSorted = ascSort(noSorted, d);
			d = d / partition;
		}
		d = 1;
		noSorted = ascSort(noSorted, d);
		return noSorted;
	}
	
	private static int[] ascSort(int[] noSorted, int d){
		for(int i = 0; i < (noSorted.length - d); i++){
			int j = i + d;
			if(j < noSorted.length && noSorted[j] < noSorted[i]){
				int temp = noSorted[j];
				while(i>= 0 && temp < noSorted[i]){
					noSorted[i+d] = noSorted[i];
					i -= d;
				}
				noSorted[i+d] = temp;
			}
			i = j - d;
		}
		return noSorted;
	}
	
	public static void main(String[] args) {
		int length = 30000000;
//		for(int i = 0; i < 3; i++){
			int[] noSorted = Generator.intArray(length);
			long start = System.currentTimeMillis();
			ascSort(noSorted);
			long end = System.currentTimeMillis();
			System.out.println(end-start);	
//		}
		Integer[] data2 = Generator.intObjArray(length);
		List<Integer> list = Arrays.asList(data2);
		long start2 = System.currentTimeMillis();
		Collections.sort(list);
		long end2 = System.currentTimeMillis();
		System.out.println(end2 - start2);
	}
	
//	public static void main(String[] args) {
//		int length = 300;
//		int[] noSorted = Generator.intArray(length);
//		for(int i : noSorted){
//			System.out.println(i);
//		}
//		System.out.println("*****************");
//		int[] sorted = ascSort(noSorted);
//		for(int i : sorted){
//			System.out.println(i);
//		}
//	}
}
