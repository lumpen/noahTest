package com.mapbar.noah.algorithm.levenshtein;

import org.apache.commons.lang.StringUtils;

public class Test2 {
	public static void main(String[] args) throws Exception{
		String str1 = "北京故宫";
		String str2 = "故宫";
		int editDis = StringUtils.getLevenshteinDistance(str1, str2);
		System.out.println(editDis);
	}
}
