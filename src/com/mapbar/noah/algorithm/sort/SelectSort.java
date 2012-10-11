package com.mapbar.noah.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 特别慢
 * @author lizn
 *
 */
public class SelectSort {

	public static int[] data;
	
	public static void ascSort(){
		for(int i = 0; i < data.length-1; i++){
			int min = data[i];
			int minIndex = i;
			for(int j = i+1; j < data.length; j++){
				if(data[j] < min){
					min = data[j];
					minIndex = j;
				}
			}
			if(minIndex != i){
				swap(data, i, minIndex);
			}
		}
	}
	
	public static int[] swap(int[] ary, int index1, int index2) {
		int temp = ary[index1];
		ary[index1] = ary[index2];
		ary[index2] = temp;
		return ary;
	}
	
	public static void main(String[] args) {
		int length = 30000000;
		data = Generator.intArray(length);
		long start = System.currentTimeMillis();
		System.out.println(start);
		ascSort();
		long end = System.currentTimeMillis();
		System.out.println(end);
		System.out.println((end - start) / 1000);

		Integer[] data2 = Generator.intObjArray(length);
		start = System.currentTimeMillis();
		System.out.println(start);
		Arrays.sort(data2);
		end = System.currentTimeMillis();
		System.out.println(end);
		System.out.println((end - start) / 1000);
	}
//	public static void main(String[] args) {
//		int length = 30;
//		data = Generator.intArray(length);
//		for (int i : data) {
//			System.out.println(i);
//		}
//		System.out.println("*****************");
//		ascSort();
//		for (int i : data) {
//			System.out.println(i);
//		}
//	}
}
