package com.learning.concurrency.exercise.multithreading.bookreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        Book[] books = new Book[Constants.NUMBER_OF_BOOKS];
        for (int iter = 0; iter < Constants.NUMBER_OF_BOOKS; iter++) books[iter] = new Book(iter + 1);
        Student[] students = new Student[Constants.NUMBER_OF_STUDENTS];

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int iter = 0; iter < Constants.NUMBER_OF_STUDENTS; iter++) {
            students[iter] = new Student(iter + 1, books);
            executorService.execute(students[iter]);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        executorService.shutdown();

    }
}
