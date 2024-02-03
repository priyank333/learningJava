package com.learning.concurrency.forkjoin.recursivetask.doubleno;

import java.util.concurrent.ForkJoinPool;

public class App {
  public static void main(String[] args) {
    DoubleNoTask task = new DoubleNoTask(100.0);
    ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    Double result = forkJoinPool.invoke(task);

    System.out.println("Result : " + result);
  }
}
