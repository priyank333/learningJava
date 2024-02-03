package com.learning.concurrency.exercise.multithreading.bookreading;

import java.util.Random;

public class Student implements Runnable {
  private int studentId;
  private Book[] books;
  private Random random;

  public Student(int studentId, Book[] books) {
    this.studentId = studentId;
    this.books = books;
    random = new Random();
  }

  @Override
  public void run() {

    while (true) {
      Book currentBook = books[random.nextInt(Constants.NUMBER_OF_BOOKS)];
      try {
        currentBook.read(this);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public String toString() {
    return "Student [studentId=" + studentId + "]";
  }
}
