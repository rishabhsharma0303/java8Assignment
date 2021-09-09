package com.day1.session2.ex4;

import java.util.*;
import java.util.stream.Collectors;

public class DishTesterExample {
	
	public static void main(String[] args) {
		List<Dish> allDishes=getAllDishes();
		//Return the names of dishes that are low in calories (<400),
		//Sorted by number of calories
		
		List<String> namesOfDishedLessThan400Cal = getAllDishes()
													.stream()
													.filter(dish->dish.getCalories() > 400)
													.sorted((o1, o2) -> Integer.compare(o1.getCalories(), o2.getCalories()))
													.map(dish -> dish.getName())
													.collect(Collectors.toList());
		namesOfDishedLessThan400Cal.forEach(System.out::println);
	}
	

	
	private static List<Dish> getAllDishes() {
		List<Dish> disheds = Arrays.asList(
				new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH) );
		return disheds;
	}
}
