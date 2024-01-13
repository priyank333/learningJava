package com.learning.concurrency.exercise.multithreading.bookreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {
    private int bookId;
    private Lock lock;

    public Book(int bookId) {
        this.bookId = bookId;
        lock = new ReentrantLock(true);
    }

    public void read(Student student) throws InterruptedException {
        if (lock.tryLock(5, TimeUnit.SECONDS)) {
            System.out.println(this + ": is reading by student : " + student);
            TimeUnit.SECONDS.sleep(2);
            lock.unlock();
            System.out.println(this + ": reading is finished by student : " + student);
        }
    }

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + "]";
    }


}
