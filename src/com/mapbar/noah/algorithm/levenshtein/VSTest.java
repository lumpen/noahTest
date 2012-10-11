package com.mapbar.noah.algorithm.levenshtein;

import org.apache.commons.lang.StringUtils;

public class VSTest {
	/**
	 * 测试正确性和性能
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//		String[] arr = new String[1000];
//		for(int i = 0; i < arr.length; i++){
//			arr[i] = RandomStrGenerator.generate();
//		}
//
//		for(int i = 1; i < arr.length; i++){
//			int dis1 = LevenshteinDistance.getDistance(arr[i-1], arr[i]);
//			int dis2 = StringUtils.getLevenshteinDistance(arr[i-1], arr[i]);
//			if(dis1 != dis2){
//				System.out.println(arr[i-1] + " " + arr[i]);
//			}
//		}
//		
//	}
	
	public static void main(String[] args) {
		String[] arr = new String[1000];
		for(int i = 0; i < arr.length; i++){
			arr[i] = RandomStrGenerator.generate();
		}
		long start1 = System.currentTimeMillis();
		for(int ii = 0; ii < 100000; ii++){
			for(int i = 1; i < arr.length; i++){
				LevenshteinDistance.getDistance(arr[i-1], arr[i]);
			}	
		}
		long end1 = System.currentTimeMillis();
		System.out.println(end1-start1);
				
		long start2 = System.currentTimeMillis();
		for(int ii = 0; ii < 100000; ii++){
			for(int i = 1; i < arr.length; i++){
				StringUtils.getLevenshteinDistance(arr[i-1], arr[i]);
			}	
		}
		long end2 = System.currentTimeMillis();
		System.out.println(end2-start2);
	}
}
