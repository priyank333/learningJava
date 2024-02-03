package com.learning.concurrency.exercise.parllelization.mergesort;

import java.util.Arrays;
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
    int[] arrForSeq = getRandomValArr(n);
    int[] arrForParallel = Arrays.copyOfRange(arrForSeq, 0, arrForSeq.length);

    long startTime = System.currentTimeMillis();
    MergeSort mergeSort = new MergeSort(arrForSeq);
    mergeSort.sort();
    long endTime = System.currentTimeMillis();
    System.out.println("Sequential Merge Sort Time : " + (endTime - startTime) + " ms");

    startTime = System.currentTimeMillis();
    MergeSort mergeSortParallel =
        new ParallelMergeSort(arrForParallel, Runtime.getRuntime().availableProcessors());
    mergeSortParallel.sort();
    endTime = System.currentTimeMillis();
    System.out.println("Parallel Merge Sort Time : " + (endTime - startTime) + " ms");
  }
}
