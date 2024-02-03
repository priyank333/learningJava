package com.learning.concurrency.forkjoin.recursivetask.doubleno;

import java.util.concurrent.RecursiveTask;

public class DoubleNoTask extends RecursiveTask<Double> {

  private Double no;

  public DoubleNoTask(Double no) {
    this.no = no;
  }

  @Override
  protected Double compute() {

    if (this.no < 2) {
      return this.no * 2;
    }

    DoubleNoTask subTask1 = new DoubleNoTask(no / 2);
    DoubleNoTask subTask2 = new DoubleNoTask(no / 2);

    subTask1.fork();
    subTask2.fork();

    Double subResult = subTask1.join() + subTask2.join();
    return subResult;
  }
}
