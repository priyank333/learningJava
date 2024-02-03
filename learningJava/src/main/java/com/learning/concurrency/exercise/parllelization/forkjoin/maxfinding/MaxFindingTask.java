package com.learning.concurrency.exercise.parllelization.forkjoin.maxfinding;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MaxFindingTask extends RecursiveTask<Integer> {

  private List<Integer> list;
  private int lowIndex;
  private int highIndex;

  public MaxFindingTask(List<Integer> list, int lowIndex, int highIndex) {
    this.list = list;
    this.lowIndex = lowIndex;
    this.highIndex = highIndex;
  }

  @Override
  protected Integer compute() {

    if (this.highIndex - this.lowIndex <= 5000) {
      return sequentialMaxFinding();
    }
    return processSubTasks();
  }

  private Integer processSubTasks() {
    int middleIndex = (lowIndex + highIndex) / 2;
    MaxFindingTask subTask1 = new MaxFindingTask(list, lowIndex, middleIndex);
    MaxFindingTask subTask2 = new MaxFindingTask(list, middleIndex + 1, highIndex);

    subTask1.fork();
    subTask2.fork();

    return Math.max(subTask1.join(), subTask2.join());
  }

  private Integer sequentialMaxFinding() {
    Integer max = list.get(lowIndex);
    for (int i = lowIndex; i <= highIndex; i++) {
      max = Math.max(max, list.get(i));
    }
    return max;
  }
}
