package com.learning.concurrency.collection;

import java.util.List;
import java.util.Vector;

public class VectorThreadSafe {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> vector = new Vector<>();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) vector.add(i);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) vector.add(i);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Size of vector : " + vector.size());
    }
}
