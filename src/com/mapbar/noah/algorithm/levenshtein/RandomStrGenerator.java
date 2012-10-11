package com.mapbar.noah.algorithm.levenshtein;

import java.util.Random;

public class RandomStrGenerator {
	public static char[] charArr = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'g',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z' };
	
	public static String generate(){
		int length = lenRam.nextInt(10);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < length; i++)
			sb.append(charArr[arrRam.nextInt(charArr.length)]);
		return sb.toString();
	}
	
	private static Random arrRam = new Random();
	private static Random lenRam = new Random(); 
}
