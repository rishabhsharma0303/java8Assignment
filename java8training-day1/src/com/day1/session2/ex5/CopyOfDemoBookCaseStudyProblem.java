package com.day1.session2.ex5;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CopyOfDemoBookCaseStudyProblem {

	public static void main(String[] args) {

		List<Book> allBooks = loadAllBooks();
		Predicate<Book> books400Pages = b -> b.getPages() > 400;
		Predicate<Book> javaBooksPredicate = book -> book.getSubject() == Subject.JAVA;
		// 1. Find books with more then 400 pages
		List<Book> booksMoreThen400Pages = allBooks.stream().filter(books400Pages).collect(Collectors.toList());
		booksMoreThen400Pages.forEach(b -> System.out.println(b));
		// 2. Find all books that are java books and more then 400 pages
		List<Book> books = allBooks.stream().filter(books400Pages.and(javaBooksPredicate)).collect(Collectors.toList());
		books.forEach(b -> System.out.println(b));

		// 3. We need the top three longest books
		List<Book> top3LongestBooks = allBooks.stream().sorted(Comparator.comparing(Book::getPages).reversed()).limit(3)
				.collect(Collectors.toList());
		top3LongestBooks.forEach(System.out::println);

		// 4. We need from the fourth to the last longest books
		// 5. We need to get all the publishing years
		List<Integer> yearsIntegers = allBooks.stream().map(b -> b.getYear()).collect(Collectors.toList());
		// 6. We need all the authors names who have written a book
		long count = allBooks.stream().flatMap(a -> a.getAuthors().stream()).map(a -> a.getName()).distinct().count();
		System.out.println("count - " + count);
		// We need all the origin countries of the authors
		List<String> allCountiesWhereAuthorBelongs = allBooks.stream().flatMap(b -> b.getAuthors().stream())
				.map(a -> a.getCountry()).distinct().collect(Collectors.toList());
		// We want the most recent published book.
		Optional<Book> min = allBooks.stream().min(Comparator.comparing(Book::getYear));
		// We want to know if all the books are written by more than one author
		boolean isAllBooksWrittenBy2Author = books.stream().allMatch(b -> b.getAuthors().size() > 1);
		// We want one of the books written by more than one author. (findAny)
		Optional<Book> multiAuthorBook = books.stream()
				.filter(b -> b.getAuthors().size() > 1).findAny();
		// We want the total number of pages published.
		Integer totalPubPages = books.stream().map(b -> b.getPages())
				.reduce(0, (a, b) -> a + b);
		// We want to know how many pages the longest book has.
		Optional<Integer> longestBookOptional = books.stream().map(b -> b.getPages()).reduce(Integer::max);
		// We want the average number of pages of the books
		Double avergaDouble = books.stream().collect(Collectors.averagingDouble(Book::getPages));
		// We want all the titles of the books
		String title = books.stream().map(b -> b.getTitle())
				.collect(Collectors.joining(" "));
		// We want the book with the higher number of authors?

		// We want a Map of book per year.

		// We want to count how many books are published per year.

	}

	private static List<Book> loadAllBooks() {
		List<Book> books = new ArrayList<Book>();
		List<Author> authors1 = Arrays.asList(new Author("raj", "gupta", "in"), new Author("ekta", "gupta", "in"));

		List<Author> authors2 = Arrays.asList(new Author("raj", "gupta", "in"));

		List<Author> authors3 = Arrays.asList(new Author("gunika", "gupta", "us"), new Author("keshav", "gupta", "us"));

		books.add(new Book("java", authors1, 400, Subject.JAVA, 2000, "1213"));
		books.add(new Book("python", authors2, 479, Subject.JAVA, 2007, "1218"));
		books.add(new Book("Mgt", authors3, 600, Subject.DOT_NET, 2000, "1293"));

		return books;
	}

}
