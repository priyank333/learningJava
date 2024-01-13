package com.learning.concurrency.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

enum Downloader {
    INSTANCE;

    Semaphore semaphore = new Semaphore(10, true);

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

    public void downloadData() throws InterruptedException {
        System.out.println("Downloading data ...");
        Thread.sleep(5000);
    }
}

public class SemaphoreDemo {
    public static void main(String[] args) {
        Downloader downloader = Downloader.INSTANCE;
        List<Thread> threads = new ArrayList<>();
        int nThreads = 1000;
        for (int i = 0; i < nThreads; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    downloader.download();
                }
            }));
        }
        for (int i = 0; i < nThreads; i++) {
            threads.get(i).start();
        }
    }
}
