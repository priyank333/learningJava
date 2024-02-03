package com.learning.concurrency.collection;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityBlockingQueueDemo {
  public static void main(String[] args) {
    BlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
    new Thread(new DataProdWorker(priorityBlockingQueue)).start();
    new Thread(new DataConsumerWorker(priorityBlockingQueue)).start();
  }
}

class DataProdWorker implements Runnable {

  private BlockingQueue<Integer> blockingQueue;

  public DataProdWorker(BlockingQueue<Integer> blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    Random random = new Random();

    while (true) {
      for (int i = 0; i < random.nextInt(13); i++) {
        try {
          int item = random.nextInt(2000);
          System.out.println("Produced Item : " + item);
          blockingQueue.put(item);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      System.out.println("Producer going to sleep ....");
      try {
        TimeUnit.SECONDS.sleep(random.nextInt(3));
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

class DataConsumerWorker implements Runnable {

  private BlockingQueue<Integer> blockingQueue;

  public DataConsumerWorker(BlockingQueue<Integer> blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    Random random = new Random();

    while (true) {
      for (int i = 0; i < random.nextInt(13); i++) {
        try {
          int retrievedItem = blockingQueue.take();
          System.out.println("Retrieved Item : " + retrievedItem);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      System.out.println("Consumer going to sleep ....");
      try {
        TimeUnit.SECONDS.sleep(random.nextInt(3));
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
