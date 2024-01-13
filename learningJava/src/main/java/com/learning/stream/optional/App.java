package com.learning.stream.optional;

import com.learning.stream.bookapp.Book;
import com.learning.stream.bookapp.BookData;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class App {
	public static void main(String[] args) {
		System.out.println("Find Max number");
		OptionalInt max = IntStream.range(0, 50000).reduce(Integer::max);
		max.ifPresent(System.out::println);

		List<Book> books = BookData.getBookData();
		System.out.println("Return Book Title whose page size is max");
		Optional<Book> book = books.stream()
				.reduce((book1, book2) -> book1.getPages() > book2.getPages() ? book1 : book2);
		book.ifPresent(x -> System.out.println(x.getTitle()));
		System.out.println("Return max book page size");
		books.stream().map(Book::getPages).reduce(Integer::max).ifPresent(System.out::println);
		System.out.println("Total pages");
		/*
		 * mapToInt : [book1, book2, book3, book4, book5, book6] ->
		 * [1,1,1,1,1,1] -> sum
		 */
		int totalPages = books.stream().mapToInt(Book::getPages).sum();
		System.out.println(totalPages);
	}
}
