package com.learning.concurrency.sleep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SleepMethodDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        });
    }
}
