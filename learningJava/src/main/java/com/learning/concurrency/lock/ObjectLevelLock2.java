package com.learning.concurrency.lock;

public class ObjectLevelLock2 {
    //Lock 1 for static int variable
    private static final Object lock1 = new Object();
    //Lock 2 for static int variable
    private static final Object lock2 = new Object();
    private static int counter1 = 0;
    private static int counter2 = 0;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                long start1 = System.currentTimeMillis();
                for (int iter = 0; iter < 100; iter++) {
                    counter1++;
                }
                long end = System.currentTimeMillis();
                System.out.println("T1 Time : " + (end - start1) + " ms");
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                long start12 = System.currentTimeMillis();
                for (int iter = 0; iter < 100; iter++) {
                    counter2++;
                }
                long end = System.currentTimeMillis();
                System.out.println("T2 Time : " + (end - start12) + " ms");
            }
        }, "t2");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("Counter1 : " + counter1);
        System.out.println("Counter2 : " + counter2);
        System.out.println("Time : " + (end - start) + " ms");
    }
}
