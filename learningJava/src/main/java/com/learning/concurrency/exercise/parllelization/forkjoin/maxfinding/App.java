package com.learning.concurrency.exercise.parllelization.forkjoin.maxfinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {
  public static void main(String[] args) {
    ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    int N = 1000;
    List<Integer> list = getRandomList(N);
    MaxFindingTask maxFindingTask = new MaxFindingTask(list, 0, list.size() - 1);
    Integer answer = forkJoinPool.invoke(maxFindingTask);
    System.out.println("Max Answer : " + answer);
  }

  private static List<Integer> getRandomList(int n) {
    List<Integer> list = new ArrayList<>();
    Random random = new Random();
    for (int iter = 0; iter < n; iter++) {
      list.add(random.nextInt(n));
    }
    return list;
  }
}
