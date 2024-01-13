package com.learning.concurrency.exercise.parllelization.forkjoin.mergesort;

import com.learning.concurrency.exercise.parllelization.mergesort.MergeSort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {
        int n = 20000000;
        int[] arrForSeq = getRandomValArr(n);
        int[] arrForParallel = Arrays.copyOfRange(arrForSeq, 0, arrForSeq.length);

        long startTime = System.currentTimeMillis();
        MergeSort mergeSort = new MergeSort(arrForSeq);
        mergeSort.sort();
        long endTime = System.currentTimeMillis();
        System.out.println("Sequential Merge Sort Time : " + (endTime - startTime) + " ms");

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        startTime = System.currentTimeMillis();
        MergeSortAction mergeSortAction = new MergeSortAction(arrForParallel);
        forkJoinPool.invoke(mergeSortAction);
        endTime = System.currentTimeMillis();
        System.out.println("Parallel Merge Sort Time : " + (endTime - startTime) + " ms");
    }

    private static int[] getRandomValArr(int n) {
        int[] arr = new int[n];
        Random random = new Random();
        for (int iter = 0; iter < n; iter++) {
            arr[iter] = random.nextInt(n);
        }
        return arr;
    }
}
