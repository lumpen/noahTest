package com.mapbar.noah.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestSort implements Comparator<TestSort>, Comparable<TestSort> {
	private int n = 0;
	@Override
	public int compare(TestSort o1, TestSort o2) {
		if(o1.n < o2.n)
			return -1;
		else if(o1.n == o2.n)
			return 0;
		else
			return 1;
	}
	
	public static void main(String[] args) {
		TestSort ts1 = new TestSort();
		TestSort ts2 = new TestSort();
		TestSort ts3 = new TestSort();
		TestSort ts4 = new TestSort();
		ts1.n = 10;
		ts2.n = 6;
		ts3.n = 9;
		ts4.n = 5;
		List<TestSort> tss = new ArrayList<TestSort>();
		tss.add(ts1);
		tss.add(ts2);
		tss.add(ts3);
		tss.add(ts4);
		Collections.sort(tss, new TestSort());
		for(TestSort ts : tss){
			System.out.println(ts.n);
		}
	}

	@Override
	public int compareTo(TestSort o) {
		if(n < o.n)
			return -1;
		else if(n == o.n)
			return 0;
		else
			return 1;
	}

}
