package com.learning.concurrency.exercise.multithreading.modeltrain;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Downloader {
  private Semaphore semaphore;
  private AtomicInteger downloadCount = new AtomicInteger(0);
  private BlockingQueue<DataPackage> downloadedPackage;

  public Downloader(int concurrentDownloadCount, BlockingQueue<DataPackage> downloadedPackage) {
    semaphore = new Semaphore(concurrentDownloadCount, true);
    this.downloadedPackage = downloadedPackage;
  }

  public void download() {
    try {
      semaphore.acquire();
      downloadData();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      semaphore.release();
    }
  }

  public void downloadData() {
    try {
      DataPackage dataPackage = new DataPackage(downloadCount.incrementAndGet());
      System.out.println("Package : " + dataPackage.packageNo + " is downloading ...");
      TimeUnit.SECONDS.sleep(3);
      downloadedPackage.put(dataPackage);
      System.out.println("Package : " + dataPackage.packageNo + " is downloaded.");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
