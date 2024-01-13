package com.learning.concurrency.forkjoin.recursivetask.fibonacci;

import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        FibonacciTask fibonacciTask = new FibonacciTask(8);
        int result = forkJoinPool.invoke(fibonacciTask);
        System.out.println("Result : " + result);
    }
}