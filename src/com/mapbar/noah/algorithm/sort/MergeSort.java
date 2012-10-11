package com.mapbar.noah.algorithm.sort;

import java.lang.reflect.Array;

/**
 * 归并排序：把已排序的两个集合合并。顺序读取第一个和第二个集合，指针在两个集合的下标之间转移。
 * 本算法采用自下而上的递归算法。
 * MergeSort 和 MergeSort2比较，不同之处在于，用于存储的临时数据是每次调用的时候初始化而不是一次申请够用的。
 * 导致速度变慢。长度10000000的数组排序一次慢一秒。（基于本机）
 * @author lizn
 *
 */
public class MergeSort {
	public static Integer[] data;

	public static void ascSort(){
		sort(data, 0, data.length-1);
	}
	
	public static void sort(Integer[] array, int from, int to){
		if(to <= from) return;
		int mid = (from+to)/2;
		sort(array, from, mid);
		sort(array, mid+1, to);
		merge(array, from, to, mid);
	}
	
	private static void merge(Integer[] array, int from, int to, int mid){
		Integer[] temp = (Integer[]) Array.newInstance(array[0].getClass(), to-from+1);
		System.arraycopy(array, from, temp, 0, mid-from+1);
		for(int i = 0; i < (to-mid); i++){
			temp[mid-from+1+i] = array[to-i];
		}
		int fromIndex = 0, toIndex = to - from, index = 0;
		while(index < (to - from + 1)){
			if(temp[fromIndex] < temp[toIndex])
				array[index+from] = temp[fromIndex++];
			else
				array[index+from] = temp[toIndex--];
			index++;
		}
	}

	public static void main(String[] args) {
		int length = 7;
		data = Generator.intObjArray(length);
//		long start = System.currentTimeMillis();
//		System.out.println(start);
		for(int i : data){
			System.out.println(i);
		}
		System.out.println("*****************");
		ascSort();
		for(int i : data){
			System.out.println(i);
		}
//		long end = System.currentTimeMillis();
//		System.out.println(end);
//		System.out.println((end - start) / 1000);
	}
}
