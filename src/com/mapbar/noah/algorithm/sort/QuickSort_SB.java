package com.mapbar.noah.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 原理,通过一趟扫描将要排序的数据分割成独立的两部分,其中一部分的所有数据都比另外一部分的所有数据都要小（一般用第一个元素做分割标识）,然后再按此方法对这两部分数据分别进行快速排序
 * ,整个排序过程可以递归进行,以此达到整个数据变成有序序列
 * sb的实现，太多的数据copy，不能用。
 * @author lizn
 * 
 */
public class QuickSort_SB {
	public static int[] ascSort(int[] noSorted){
		if(noSorted.length == 1 || noSorted.length == 0)
			return noSorted;
		int flag = noSorted[0];
		int index = 0;
		for(int i = 1; i < noSorted.length; i++){
			if(noSorted[i] < flag){
				int temp = noSorted[i];
				for(int j = i; j > 0; j--){
					noSorted[j] = noSorted[j-1];
				}
				noSorted[0] = temp;
				index++;
			}
		}
		if(index == 0)
			return noSorted;
		else{
			int[] head = ascSort(getHeadSub(noSorted, index));
			int[] end = ascSort(getEndSub(noSorted, index));
			return arrAdd(arrAdd(head, new int[]{flag}), end);	
		}
	}

	public static void main(String[] args) {
		int length = 3000000;
		int[] noSorted = Generator.intArray(length);
//		for(int i : noSorted){
//			System.out.println(i);
//		}
//		System.out.println("*****************");
		long start = System.currentTimeMillis();
		System.out.println(start);
//		for(int i = 0; i < 1; i++){
			ascSort(noSorted);
//		}
		long end = System.currentTimeMillis();
		System.out.println(end);
		System.out.println((end-start)/1000);
//		for(int i : sorted){
//			System.out.println(i);
//		}
	}
	
	public static int[] getHeadSub(int[] all, int index){
		return Arrays.copyOfRange(all, 0, index);
	}
	
	public static int[] getEndSub(int[] all, int index){
		return Arrays.copyOfRange(all, index+1, all.length);
	}
	
	public static int[] arrAdd(int[] arr1, int[] arr2){
		int[] all = new int[arr1.length + arr2.length];
		for(int i = 0; i < arr1.length; i++){
			all[i] = arr1[i];
		}
		for(int i = 0; i < arr2.length; i++){
			all[i+arr1.length] = arr2[i];
		}
		return all;
	}
}
