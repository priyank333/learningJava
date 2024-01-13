package com.learning.stream.matches;

import com.learning.stream.bookapp.Book;
import com.learning.stream.bookapp.BookData;
import com.learning.stream.bookapp.Type;

import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Book> books = BookData.getBookData();
        // allMatch() -> checking if predicate matches all elements
        /*
         * No need to consider all list because if any single item is found not
         * match it return false. Short-Circuiting: Here no need to process
         * whole list
         */
        boolean isThere = books.stream().allMatch(b -> b.getPages() > 2000);
        System.out.println("Book pages with > 2000 : " + isThere);
        /**
         * noneMatch() -> checking if predicate not matches all elements
         * No need to consider all list because if any single item is found not
         * match it return false. Short-Circuiting: Here no need to process
         * whole list
         */
        isThere = books.stream().noneMatch(b -> b.getPages() > 2000);
        System.out.println("Book pages with > 2000 (NoneMatch): " + isThere);

        /**
         * findAny() : Search from multiple sublist and if it finds any object, it breaks the process and return.
         * Short-Circuiting: Here no need to process whole list
         */
        System.out.println("FindAny :: Novel Book");
        books.stream().filter(x -> x.getType() == Type.NOVEL).findAny().ifPresent(System.out::println);
        // findFirst() : Search sequentially and return first element
        // the process and return.
        /**
         * Short-Circuiting: Here no need to process whole list
         */
        System.out.println("FindFirst :: Novel Book");
        books.stream().filter(x -> x.getType() == Type.NOVEL).findFirst().ifPresent(System.out::println);
    }
}
