package com.mapbar.noah.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序 把一个数组构造成一个二叉堆（其实就是完全二叉树），比较父子节点的大小，进行排序，直到整个数组排序完成。 完全二叉树父子节点对应的数组下标计算方法：
 * parent(i) = i/2; left(i) = 2i; right(i) = 2i+1; 针对java，数组下标都是从0开始，则计算方法：
 * parent(i) = (i-1)/2; left(i) = 2i+1; right(i) = 2(i+1);
 * 
 * @author lizn
 * 
 */
public class HeapSort {

	public static int[] data;
	/**
	 * 先调用buildMaxHeap将数组改造为最大堆，然后将堆顶和堆底元素交换，之后将底部上升，最后重新调用MaxHeapify保持最大堆性质。
	 * 由于堆顶元素必然是堆中最大的元素，所以一次操作之后，堆中存在的最大元素被分离出堆。
	 * 重复n-1次之后，数组排列完毕。
	 */
	public static void ascSort(){
		int nHeapSize = data.length - 1;
		buildMaxHeap(nHeapSize);
		for(int i = nHeapSize; i >= 1; --i){
			swap(data, 0, i);
			--nHeapSize;
			maxHeapify(data, 0, nHeapSize);
		}
	}

	/**
	 * 从最后一个数组元素开始，调用maxHeapify，自下而上完成整个数组的最大堆构造
	 * 最大堆：每个父节点元素都不小于子节点元素（如果存在）
	 * 
	 * @param ary
	 * @param nHeapSize
	 */
	public static void buildMaxHeap(int nHeapSize) {
		for (int i = parent(nHeapSize); i >= 0; --i) {
			maxHeapify(data, i, nHeapSize);
		}
	}

	/**
	 * 给定一个数组下标，构造此数组下标对应元素为根节点的子堆为最大堆 递归调用
	 * 
	 * @param ary
	 * @param nIndex
	 * @param nHeapSize
	 */
	public static void maxHeapify(int[] ary, int nIndex, int nHeapSize) {
		int nL = left(nIndex);
		int nR = right(nIndex);
		int nLargest = nIndex;
		if (nL <= nHeapSize && ary[nLargest] < ary[nL])
			nLargest = nL;
		if (nR <= nHeapSize && ary[nLargest] < ary[nR])
			nLargest = nR;
		if (nLargest != nIndex) {
			ary = swap(ary, nLargest, nIndex);
			maxHeapify(ary, nLargest, nHeapSize);
		}
	}	

	public static int[] swap(int[] ary, int index1, int index2) {
		int temp = ary[index1];
		ary[index1] = ary[index2];
		ary[index2] = temp;
		return ary;
	}

	public static int left(int index) {
		return 2 * index + 1;
	}

	public static int right(int index) {
		return 2 * (index + 1);
	}

	public static int parent(int index) {
		return (index - 1) / 2;
	}

//	public static void main(String[] args) {
//		int length = 30;
//		data = Generator.intArray(length);
//		for (int i : data) {
//			System.out.println(i);
//		}
//		System.out.println("*****************");
//		// long start = System.currentTimeMillis();
//		// System.out.println(start);
//		ascSort();
//		// long end = System.currentTimeMillis();
//		// System.out.println(end);
//		// System.out.println(end-start);
//		for (int i : data) {
//			System.out.println(i);
//		}
//	}
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
}
