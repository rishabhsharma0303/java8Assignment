package com.day1.session1.ex2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.transform.Source;

public class DemoLambdaExpession2 {

	public static void main(String[] args) {

		List<Book> books = Arrays.asList(new Book(121, "java", "raj", 240), new Book(11, "python", "ekta", 840),
				new Book(621, "c programming", "gunika", 300));
		Predicate<Book> predicate = new Predicate<Book>() {
			@Override
			public boolean test(Book t) {
				return t.getTitle() == "java";
			}
		};

		List<Book> listPrice = books.stream().filter(b -> b.getPrice() >= 300).collect(Collectors.toList());
		listPrice.forEach(b -> System.out.println(b.getPrice()));
	}
}
