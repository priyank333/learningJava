package com.learning.concurrency.exercise.multithreading.modeltrain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelDataProcessor {

  private BlockingQueue<DataPackage> downloadedData;
  private int threadCount;
  private int downloadPackageCount;

  public ParallelDataProcessor(
      BlockingQueue<DataPackage> downloadedData, int threadCount, int downloadPackageCount) {
    this.downloadedData = downloadedData;
    this.threadCount = threadCount;
    this.downloadPackageCount = downloadPackageCount;
  }

  public void process() {
    List<Thread> threads = new ArrayList<>();
    AtomicInteger processedCount = new AtomicInteger(0);
    for (int i = 0; i < threadCount; i++) {
      threads.add(
          new Thread(new DataProcessor(downloadedData, downloadPackageCount, processedCount)));
    }
    threads.forEach(Thread::start);
  }
}
