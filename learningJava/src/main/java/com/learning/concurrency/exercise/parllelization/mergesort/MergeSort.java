package com.learning.concurrency.exercise.parllelization.mergesort;

import java.util.Arrays;

public class MergeSort {
  protected int nums[];
  private int tempArr[];

  public MergeSort(int[] nums) {
    this.nums = nums;
    this.tempArr = new int[nums.length];
  }

  protected void merge(int low, int middleIdx, int high) {
    for (int i = low; i <= high; i++) {
      tempArr[i] = nums[i];
    }

    int i = low;
    int j = middleIdx + 1;
    int k = low;

    while (i <= middleIdx && j <= high) {
      if (tempArr[i] < tempArr[j]) {
        nums[k] = tempArr[i];
        i++;
      } else {
        nums[k] = tempArr[j];
        j++;
      }
      k++;
    }
    while (i <= middleIdx) {
      nums[k] = tempArr[i];
      k++;
      i++;
    }

    while (j <= high) {
      nums[k] = tempArr[j];
      k++;
      j++;
    }
  }

  protected void mergeSort(int low, int high) {
    if (low >= high) return;

    int middleIdx = (low + high) / 2;
    mergeSort(low, middleIdx);
    mergeSort(middleIdx + 1, high);
    merge(low, middleIdx, high);
  }

  public void show() {
    Arrays.stream(this.nums).forEach(no -> System.out.print(no + " "));
    System.out.println();
  }

  public void sort() {
    mergeSort(0, nums.length - 1);
  }
}
