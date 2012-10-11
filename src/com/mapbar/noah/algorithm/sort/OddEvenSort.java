package com.mapbar.noah.algorithm.sort;

import java.util.Arrays;

/**
 * 奇偶排序。
 * 分别按奇数和偶数的下标遍历数组，比较相邻的元素大小，直到排序完成。
 * 目前的实现效率很低。
 * @author lizn
 *
 */
public class OddEvenSort {

	public static int[] data;

	public static void ascSort() {
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < data.length - 1; i += 2) {
				if (data[i] > data[i + 1]) {
					swap(data, i, i + 1);
					sorted = false;
				}
			}
			for (int i = 1; i < data.length - 1; i += 2) {
				if (data[i] > data[i + 1]) {
					swap(data, i, i + 1);
					sorted = false;
				}
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
		int length = 300000;
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
