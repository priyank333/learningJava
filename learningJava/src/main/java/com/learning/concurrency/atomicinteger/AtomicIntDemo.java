package com.learning.concurrency.atomicinteger;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntDemo {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(task.atomicInteger.get());
    }
}

class Task implements Runnable {
    public AtomicInteger atomicInteger = new AtomicInteger(0);

    private void increment() {
        for (int i = 0; i < 1000; i++)
            atomicInteger.addAndGet(1);
    }

    @Override
    public void run() {
        increment();
    }
}