package com.learning.concurrency.exercise.parllelization.arraysum;

public class ArraySum {

  protected long ans;
  protected int[] arr;

  public ArraySum(int[] arr) {
    this.arr = arr;
  }

  public void findSum() {
    long sum = 0;
    for (int iter = 0; iter < arr.length; iter++) {
      sum += arr[iter];
    }
    ans = sum;
  }

  public long getAns() {
    return ans;
  }
}
