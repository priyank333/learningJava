package com.learning.concurrency.prodcons;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {
    public static void main(String[] args) {
        Task task = new Task();
        Thread producerThread = new Thread(() -> {
            try {
                task.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumerThread = new Thread(() -> {
            try {
                task.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producerThread.start();
        consumerThread.start();
    }
}

class Task {
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private List<Integer> list = new ArrayList<>();
    private Object lock = new Object();
    private int counter = 1;

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for removing items ...");
                    //Put current thread on wait stage.
                    lock.wait();
                } else {
                    System.out.println("Adding item : " + counter);
                    list.add(counter++);
                    //Notify the locked thread
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for adding items ...");
                    lock.wait();
                } else {
                    System.out.println("Removing item : " + list.get(list.size() - 1));
                    list.remove(list.size() - 1);
                    counter--;
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}