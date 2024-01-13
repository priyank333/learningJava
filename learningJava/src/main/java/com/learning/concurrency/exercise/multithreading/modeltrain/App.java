package com.learning.concurrency.exercise.multithreading.modeltrain;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
    public static void main(String[] args) {
        BlockingQueue<DataPackage> downloadedData = new ArrayBlockingQueue<>(Constants.QUEUE_SIZE);
        DownloadManager downloadManager = new DownloadManager();
        downloadManager.startDownload(Constants.DOWNLOAD_PACKAGE_COUNT, downloadedData);
        ParallelDataProcessor parallelDataProcessor = new ParallelDataProcessor(downloadedData,
                Constants.CONCURRENT_PROCESS_COUNT,
                Constants.DOWNLOAD_PACKAGE_COUNT);
        parallelDataProcessor.process();
    }
}
