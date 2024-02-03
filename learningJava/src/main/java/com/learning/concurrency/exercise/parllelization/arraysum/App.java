package com.learning.concurrency.exercise.parllelization.arraysum;

import java.util.Random;

public class App {
  private static int[] getRandomValArr(int n) {
    int[] arr = new int[n];
    Random random = new Random();
    for (int iter = 0; iter < n; iter++) {
      arr[iter] = random.nextInt(n);
    }
    return arr;
  }

  public static void main(String[] args) {
    int n = 100000000;
    int[] arr = getRandomValArr(n);

    long startTime = System.currentTimeMillis();
    ArraySum parallelSum = new ParallelArraySum(arr, Runtime.getRuntime().availableProcessors());
    parallelSum.findSum();
    System.out.println("Parallel Sum Answer : " + parallelSum.getAns());
    long endTime = System.currentTimeMillis();
    System.out.println("Parallel Sum Time : " + (endTime - startTime) + " ms");

    startTime = System.currentTimeMillis();
    ArraySum seqSum = new ArraySum(arr);
    seqSum.findSum();
    endTime = System.currentTimeMillis();
    System.out.println("Sequential Sum Answer : " + seqSum.getAns());
    System.out.println("Sequential Sum Time : " + (endTime - startTime) + " ms");
  }
}
