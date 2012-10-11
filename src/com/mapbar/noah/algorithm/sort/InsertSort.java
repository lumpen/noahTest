package com.mapbar.noah.algorithm.sort;

/**
 * 插入排序
 * 把一个集合分为有序区和无序区。每次都从无序区取一个往有序区里插，比较后插到合适的位置。
 * @author lizn
 *
 */
public class InsertSort {
	public static int[] ascSort(int[] noSorted){
		for(int i = 0; i < (noSorted.length-1); i++){
			int j = i+1;
			if(noSorted[j] < noSorted[i]){
				int temp = noSorted[j];
				while(i >= 0 && temp < noSorted[i]){
					noSorted[i+1] = noSorted[i];
					i--;
				}
				noSorted[i+1] = temp;
			}
			i = j-1;
		}
		return noSorted;
	}
	
	public static void main(String[] args) {
		int length = 30;
		int[] noSorted = Generator.intArray(length);
		for(int i : noSorted){
			System.out.println(i);
		}
		System.out.println("*****************");
//		long start = System.currentTimeMillis();
//		System.out.println(start);
		int[] sorted = ascSort(noSorted);
//		long end = System.currentTimeMillis();
//		System.out.println(end);
//		System.out.println(end-start);
		for(int i : sorted){
			System.out.println(i);
		}
	}
}
