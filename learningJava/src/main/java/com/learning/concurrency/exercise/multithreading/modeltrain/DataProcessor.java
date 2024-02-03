package com.learning.concurrency.exercise.multithreading.modeltrain;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DataProcessor implements Runnable {
  private BlockingQueue<DataPackage> downloadedData;
  private int downloadPackageCount;
  private AtomicInteger processedCount;

  public DataProcessor(
      BlockingQueue<DataPackage> downloadedData,
      int downloadPackageCount,
      AtomicInteger processedCount) {
    this.downloadedData = downloadedData;
    this.downloadPackageCount = downloadPackageCount;
    this.processedCount = processedCount;
  }

  public void process() {

    while (this.processedCount.get() != downloadPackageCount) {
      DataPackage receivedData = downloadedData.poll();
      if (receivedData != null) {
        System.out.println("Package : " + receivedData.packageNo + " is processed.");
        processedCount.addAndGet(1);
      } else {
        try {
          TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  @Override
  public void run() {
    process();
  }
}
