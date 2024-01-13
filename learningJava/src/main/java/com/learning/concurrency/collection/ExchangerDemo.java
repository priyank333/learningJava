package com.learning.concurrency.collection;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Exchanger;

import static java.util.concurrent.CompletableFuture.runAsync;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> stringExchanger = new Exchanger<>();

        Runnable taskA = () -> {
            try {
                String message = stringExchanger.exchange("I am calling from A");
                System.out.println("Message from B : " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };
        Runnable taskB = () -> {
            try {
                String message = stringExchanger.exchange("I am calling from B");
                System.out.println("Message from A : " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        CompletableFuture.allOf(runAsync(taskA), runAsync(taskB)).join();
    }
}
