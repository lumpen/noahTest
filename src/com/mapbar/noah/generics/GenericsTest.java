package com.mapbar.noah.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsTest {
	public static void main(String[] args) {
		Apple apple = new Apple();
		List<Apple> apples = new ArrayList<Apple>();
		apples.add(apple);
//		List<? extends Fruit> fruits = apples;
//		fruits.add(apple);
		List<? extends Fruit> fruits = new ArrayList<Apple>();
//		fruits.add(apple);
	}
}
