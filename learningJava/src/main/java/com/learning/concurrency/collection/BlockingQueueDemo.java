package com.learning.concurrency.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
  public static void main(String[] args) {
    BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
    Thread dataProducerThread = new Thread(new DataProducerTask(blockingQueue, 4));
    Thread dataConsumerThread = new Thread(new DataConsumerTask(blockingQueue));
    dataProducerThread.start();
    dataConsumerThread.start();
  }
}

class DataProducerTask implements Runnable {

  public BlockingQueue<Integer> blockingQueue;
  public int dataSize;

  public DataProducerTask(BlockingQueue blockingQueue, int dataSize) {
    this.blockingQueue = blockingQueue;
    this.dataSize = dataSize;
  }

  @Override
  public void run() {

    int count = 1;

    for (int i = 0; i < dataSize; i++) {
      try {
        System.out.println("Produced Data : " + count + "; Capacity : " + blockingQueue.size());
        blockingQueue.put(count);
        count++;
        TimeUnit.MILLISECONDS.sleep(500);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

class DataConsumerTask implements Runnable {

  public BlockingQueue<Integer> blockingQueue;

  public DataConsumerTask(BlockingQueue<Integer> blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        int count = blockingQueue.take();
        System.out.println("Received Data : " + count + "; Capacity : " + blockingQueue.size());
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
