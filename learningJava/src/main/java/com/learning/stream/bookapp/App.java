package com.learning.stream.bookapp;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<Book> books = BookData.getBookData();

        // Get list of books those types are novel
        System.out.println("List of books which type is Novel by :: filter(x -> x.getType().equals(Type.NOVEL))");
        List<Book> novelBooks = books.stream().filter(x -> x.getType().equals(Type.NOVEL)).collect(Collectors.toList());
        novelBooks.forEach(System.out::println);

        // Get list of books those types are novel in sorted manner

        List<Book> novelSortedBooksByAuthor = books.stream().filter(x -> x.getType().equals(Type.NOVEL))
                .sorted(Comparator.comparing(Book::getAuthor)).collect(Collectors.toList());

        System.out.println(
                "List of books which type is Novel sort by author :: filter(x -> x.getType().equals(Type.NOVEL)).sorted(Comparator.comparing(Book::getAuthor))");
        novelSortedBooksByAuthor.forEach(System.out::println);

        // Get list of books title those types are novel in sorted manner

        List<String> novelBookTitles = books.stream().filter(x -> x.getType().equals(Type.NOVEL))
                .sorted(Comparator.comparing(Book::getAuthor)).map(Book::getTitle).collect(Collectors.toList());
        System.out.println(
                "List of books title which type is Novel sort by author :: filter(x -> x.getType().equals(Type.NOVEL)).sorted(Comparator.comparing(Book::getAuthor)).map(Book::getTitle)");
        novelBookTitles.forEach(System.out::println);

        Map<Type, List<Book>> groupByType = books.stream().collect(Collectors.groupingBy(Book::getType));
        groupByType.entrySet().forEach(System.out::println);

        // External and Internal Iteration

        // External Iteration
        System.out.println(
                "External Iteration");
        /*
         * Let's say we have item in memory
         * [item1, item2, item3, item4, item5]
         * In memory item1 and item5 are located with next each other but it won't fetch item5. It will fetch in sequence.
         * No parallelization
         * */
        Iterator<Book> iterator = books.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("Iternal Iteration");
        /*
         * Internal Iteration
         * Parallelization
         * */
        List<String> bookTitles = books.stream().map(Book::getTitle).collect(Collectors.toList());
        bookTitles.forEach(System.out::println);

        System.out.println("Longest 2 books");
        books.stream().sorted(Comparator.comparing(Book::getPages).reversed()).limit(2).forEach(System.out::println);

    }
}
