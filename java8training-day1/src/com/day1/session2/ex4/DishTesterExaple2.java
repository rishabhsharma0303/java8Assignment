package com.day1.session2.ex4;

import java.nio.MappedByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.day1.session2.ex4.Dish.Type;

public class DishTesterExaple2 {

	public DishSelectedField getDishSelectedField(Dish d) {
		return new DishSelectedField(d.getName(), d.getCalories());
	}

	public static void main(String[] args) {

		List<Dish> allDishes = getAllDishes();
		// Example: return the names of dishes that are low in calories (<400) sorted by
		// number of calories
		List<Dish> dishLessThan400 = allDishes.stream().parallel().filter(d -> d.getCalories() < 400)
				.sorted(Comparator.comparing(Dish::getCalories)).collect(Collectors.toList());
//		System.out.println(dishLessThan400);

		// Getting all veg dishes
		List<Dish> vegDishes = allDishes.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
//		vegDishes.forEach(d -> System.out.println(d));

		// Get list of All Dishes only containing name and calValue
		Function<Dish, DishSelectedField> selectedTabs = d -> new DishSelectedField(d.getName(), d.getCalories());

		List<DishSelectedField> list = allDishes.stream().map(selectedTabs).collect(Collectors.toList());
//		list.forEach(d -> System.out.println(d.getName() + ": " + d.getCalories()));

		// create a List by selecting the first three dishes that have more than 300
		// calories

		List<Dish> moreThan300Cal = allDishes.stream().filter(b -> b.getCalories() > 300).limit(3)
				.collect(Collectors.toList());
		/*
		 * allMatch, anyMatch,noneMatch, findFirst, findAny
		 */

		// find out whether the menu has a vegetarian option: anyMatch

		boolean isVeg = allDishes.stream().anyMatch(Dish::isVegetarian);

		// find out whether the menu ishealthy :allMatch
		// (ie. all dishes are below 1000 calories):
		boolean isHealty = allDishes.stream().allMatch(b -> b.getCalories() < 1000);
		System.out.println(isHealty);

		// noneMatch: opposite of allMatch
		boolean isAllHealthy = allDishes.stream().noneMatch(b -> b.getCalories() >= 1000);

		// find if any food item is veg? findAny
		Optional<Dish> findAny = allDishes.stream().filter(b -> b.isVegetarian()).findAny();
		System.out.println(findAny.map(d -> d.getName()).orElse("Not found"));
		// Primitive stream specializations

		// IntStream,DoubleStream, and LongStream==> avoide boxing cost

		// get all the cal values of all food items
		int sum = allDishes.stream().map(Dish::getCalories).reduce(0, Integer::sum);
		System.out.println("summation :" + sum);

		// using primitive stream

		int sum2 = allDishes.stream().mapToInt(d -> d.getCalories()).sum();
		System.out.println("sum -" + sum2);
		// find max cal values for all dishes, Optional
		/*
		 * What type of quaries Collect helps:Collectors.groupingBy(..)
		 * ------------------------------------- 1. Dishes grouped by type 2. Dishes
		 * grouped by caloric level 3. Dishes grouped by type and then caloric level 4.
		 * Count dishes in each groups 5. Most caloric dishes by type 6. Sum calories by
		 * type
		 */

		// Dishes grouped by type

		Map<Dish.Type, List<Dish>> mapByType = allDishes.stream().collect(Collectors.groupingBy(d -> d.getType()));
		System.out.println(mapByType);
		// Dishes grouped by calorific level
		/*
		 * if (dish.getCalories() <= 400) return CaloricLevel.DIET; else if
		 * (dish.getCalories() <= 700) return CaloricLevel.NORMAL; else return
		 * CaloricLevel.FAT;
		 * 
		 */

		// Dishes grouped by type and then caloric level
		allDishes.stream().collect(Collectors.groupingBy((Dish dish) -> {
			if (dish.getCalories() <= 400)
				return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700)
				return CaloricLevel.NORMAL;
			else
				return CaloricLevel.FAT;

		}));

	}

	private static List<Dish> getAllDishes() {
		List<Dish> disheds = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));
		return disheds;
	}

}
