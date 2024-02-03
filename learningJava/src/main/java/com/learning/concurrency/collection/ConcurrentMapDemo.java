package com.learning.concurrency.collection;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ConcurrentMapDemo {
  public static void main(String[] args) {
    Map<Integer, Integer> concurrentMap = new ConcurrentHashMap<>();
    new Thread(new ProducerConcurrentTask(concurrentMap)).start();
    new Thread(new ConsumerConcurrentTask(concurrentMap)).start();
  }
}

class ProducerConcurrentTask implements Runnable {

  public Map<Integer, Integer> concurrentMap;

  public ProducerConcurrentTask(Map<Integer, Integer> concurrentMap) {
    this.concurrentMap = concurrentMap;
  }

  @Override
  public void run() {
    Random random = new Random();
    int countKey = 1;
    while (true) {
      int val = random.nextInt(5000);
      System.out.println("Producer : Key : " + countKey + "; Value : " + val);
      concurrentMap.put(countKey, val);
      countKey++;
      try {
        TimeUnit.MILLISECONDS.sleep(500);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

class ConsumerConcurrentTask implements Runnable {

  public Map<Integer, Integer> concurrentMap;

  public ConsumerConcurrentTask(Map<Integer, Integer> concurrentMap) {
    this.concurrentMap = concurrentMap;
  }

  @Override
  public void run() {

    while (true) {

      for (Map.Entry<Integer, Integer> entry : concurrentMap.entrySet()) {
        System.out.println("Consumer : Key : " + entry.getKey() + "; Value : " + entry.getValue());
      }
      concurrentMap.clear();
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
