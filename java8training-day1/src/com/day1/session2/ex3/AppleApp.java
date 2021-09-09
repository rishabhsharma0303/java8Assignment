package com.day1.session2.ex3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AppleApp {
	public static List<Apple> filterApples(List<Apple> a, Predicate<Apple> p) {
		List<Apple> applesOnCondition = new ArrayList<Apple>();
		for(Apple apple : a) {
			if(p.test(apple)) {
				applesOnCondition.add(apple);
			}
		}
		return applesOnCondition;
	}
}
