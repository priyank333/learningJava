package com.learning.concurrency.forkjoin.recursivetask.fibonacci;

import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<Integer> {

  private Integer no;

  public FibonacciTask(Integer no) {
    this.no = no;
  }

  @Override
  protected Integer compute() {

    if (this.no <= 1) {
      return this.no;
    }

    FibonacciTask subTask1 = new FibonacciTask(this.no - 1);
    FibonacciTask subTask2 = new FibonacciTask(this.no - 2);

    subTask1.fork();
    subTask2.fork();

    int result = subTask1.join() + subTask2.join();
    return result;
  }
}
