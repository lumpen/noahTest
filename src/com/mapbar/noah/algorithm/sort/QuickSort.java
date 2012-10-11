package com.mapbar.noah.algorithm.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * 快速排序 原理,通过一趟扫描将要排序的数据分割成独立的两部分,其中一部分的所有数据都比另外一部分的所有数据都要小（一般用第一个元素做分割标识）,
 * 然后再按此方法对这两部分数据分别进行快速排序 ,整个排序过程可以递归进行,以此达到整个数据变成有序序列
 * 
 * @author lizn
 * 
 */
public class QuickSort {
	private static int[] data;

	public static void ascSort(int low, int hight) {
		if (low < hight) {
			int result = partition(data, low, hight);
			ascSort(low, result - 1);
			ascSort(result + 1, hight);
		}
	}

	private static int partition(int[] array, int low, int hight) {
		int flag = array[low];
		while (low < hight) {
			while (low < hight && array[hight] >= flag)
				hight--;
			array[low] = array[hight];
			while (low < hight && array[low] <= flag)
				low++;
			array[hight] = array[low];
		}
		array[low] = flag;
		return low;
	}

	public static void main(String[] args) {
		int length = 30000000;
		data = Generator.intArray(length);
		// for(int i : data){
		// System.out.println(i);
		// }
		// System.out.println("*****************");
		long start = System.currentTimeMillis();
//		System.out.println(start);
		// for(int i = 0; i < 1; i++){
		ascSort(0, data.length - 1);
		// }
		long end = System.currentTimeMillis();
//		System.out.println(end);
		System.out.println((end - start) / 1000);
		// for(int i : data){
		// System.out.println(i);
		// }
		
		Integer[] data2 = Generator.intObjArray(length);
		List<Integer> list = Arrays.asList(data2);
		long start2 = System.currentTimeMillis();
		Collections.sort(list);
		long end2 = System.currentTimeMillis();
		System.out.println((end2 - start2) / 1000);
	}
}
