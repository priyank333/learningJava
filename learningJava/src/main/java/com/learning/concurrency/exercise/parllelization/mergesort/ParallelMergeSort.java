package com.learning.concurrency.exercise.parllelization.mergesort;

public class ParallelMergeSort extends MergeSort {

  private final int THREADS_COUNT;

  public ParallelMergeSort(int[] nums, int numOfThreads) {
    super(nums);
    this.THREADS_COUNT = numOfThreads;
  }

  private Thread mergeSortThread(int low, int high, int numOfThreads) {
    return new Thread(
        new Runnable() {

          @Override
          public void run() {
            try {
              parallelMergeSort(low, high, numOfThreads / 2);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });
  }

  private void parallelMergeSort(int low, int high, int numOfThreads) throws InterruptedException {
    if (numOfThreads <= 1) {
      mergeSort(low, high);
      return;
    }
    int middleIdx = (low + high) / 2;
    Thread leftSorter = mergeSortThread(low, middleIdx, numOfThreads);
    Thread rightSorter = mergeSortThread(middleIdx + 1, high, numOfThreads);
    leftSorter.start();
    rightSorter.start();

    leftSorter.join();
    rightSorter.join();

    merge(low, middleIdx, high);
  }

  @Override
  public void sort() {
    try {
      parallelMergeSort(0, nums.length - 1, THREADS_COUNT);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
