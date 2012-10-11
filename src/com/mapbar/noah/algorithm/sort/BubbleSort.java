package com.mapbar.noah.algorithm.sort;

/**
 * 冒泡排序
 * 遍历数组，取数组元素与后面的所有比较。
 * @author lizn
 *
 */
public class BubbleSort {

	/**
	 * 小的就冒上来了
	 * @param noSorted
	 * @return
	 */
	public static int[] ascSort(int[] noSorted){
		for(int i = 0; i < (noSorted.length - 1); i++){
			for(int j = i+1; j < noSorted.length; j++){
				if(noSorted[i] > noSorted[j]){
					int temp = noSorted[i];
					noSorted[i] = noSorted[j];
					noSorted[j] = temp;
				}	
			}
		}
		return noSorted;
	}
	
	/**
	 * 大的就沉底了
	 * @param noSorted
	 * @return
	 */
	public static int[] ascSort2(int[] noSorted){
		for(int i = (noSorted.length - 1); i > 0; i--){
			for(int j = 0; j < i; j++){
				if(noSorted[j] > noSorted[j+1]){
					int temp = noSorted[j];
					noSorted[j] = noSorted[j+1];
					noSorted[j+1] = temp;
				}
			}
		}
		return noSorted;
	}
	
	public static void main(String[] args) {
		int length = 300000;
		int[] noSorted = Generator.intArray(length);
//		for(int i : noSorted){
//			System.out.println(i);
//		}
//		System.out.println("*****************");
		long start = System.currentTimeMillis();
		System.out.println(start);
		int[] sorted = ascSort2(noSorted);
		long end = System.currentTimeMillis();
		System.out.println(end);
		System.out.println(end-start);
//		for(int i : sorted){
//			System.out.println(i);
//		}
	}
}
