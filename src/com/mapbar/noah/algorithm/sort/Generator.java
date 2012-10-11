package com.mapbar.noah.algorithm.sort;

import java.util.Random;

public class Generator {
	
	public static int[] intArray(int length){
		int[] array = new int[length];
		Random ran = new Random();
		for(int i = 0; i < length; i++){
			array[i] = ran.nextInt(10*length);
		}
		return array;
	}
	
	public static Integer[] intObjArray(int length){
		Integer[] array = new Integer[length];
		Random ran = new Random();
		for(int i = 0; i < length; i++){
			array[i] = ran.nextInt(10*length);
		}
		return array;
	}
}
