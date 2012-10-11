package com.mapbar.noah.algorithm.sort;

import java.lang.reflect.Array;

public class MergeSort2 {
	public static void sort(Integer[] array, int from, int len) {
		if (len <= 1)
			return;
		Integer[] temporary = (Integer[]) Array.newInstance(array[0].getClass(), len);
		merge_sort(array, from, from + len - 1, temporary);
	}

	private static final void merge_sort(Integer[] array, int from, int to,	Integer[] temporary) {
		if (to <= from) {
			return;
		}
		int middle = (from + to) / 2;
		merge_sort(array, from, middle, temporary);
		merge_sort(array, middle + 1, to, temporary);
		merge(array, from, to, middle, temporary);
	}

	private static final void merge(Integer[] array, int from, int to, int middle, Integer[] temporary) {
		int k = 0, leftIndex = 0, rightIndex = to - from;
		System.arraycopy(array, from, temporary, 0, middle - from + 1);
		for (int i = 0; i < to - middle; i++) {
			temporary[to - from - i] = array[middle + i + 1];
		}
		while (k < to - from + 1) {
			if (temporary[leftIndex].compareTo(temporary[rightIndex]) < 0) {
				array[k + from] = temporary[leftIndex++];
			} else {
				array[k + from] = temporary[rightIndex--];
			}
			k++;
		}
	}
	
//	public static void main(String[] args) {
//		Integer[] array = {1,2,3, 4,5,6};
//		Integer[] temporary = (Integer[]) Array.newInstance(array[0].getClass(), array.length);
//		merge(array, 0, array.length-1, (array.length-1)/2, temporary);
//	}

	public static void main(String[] args) {
		int length = 10000000;
		Integer[] data = Generator.intObjArray(length);
		long start = System.currentTimeMillis();
		System.out.println(start);
		sort(data, 0, data.length);
		long end = System.currentTimeMillis();
		System.out.println(end);
		System.out.println((end - start) / 1000);
		
//		Integer[] data2 = Generator.intArray2(length);
//		start = System.currentTimeMillis();
//		System.out.println(start);
//		Arrays.sort(data2);
//		end = System.currentTimeMillis();
//		System.out.println(end);
//		System.out.println((end - start) / 1000);
	}
}
