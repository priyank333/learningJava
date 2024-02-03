package com.learning.concurrency.exercise.multithreading.modeltrain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class DownloadManager {

  private BlockingQueue<DataPackage> downloadedData;

  public void startDownload(int downloadPackageCount, BlockingQueue<DataPackage> downloadedData) {
    this.downloadedData = downloadedData;
    Downloader downloader =
        new Downloader(Constants.CONCURRENT_DOWNLOAD_COUNT, this.downloadedData);
    List<Thread> totalThreads = new ArrayList<>();
    for (int i = 0; i < downloadPackageCount; i++) {
      totalThreads.add(new Thread(() -> downloader.download()));
    }
    totalThreads.forEach(Thread::start);
  }
}
