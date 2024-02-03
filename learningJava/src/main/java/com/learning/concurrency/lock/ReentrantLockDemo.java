package com.learning.concurrency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
  private Lock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();

  public static void main(String[] args) {

    ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();

    Thread producerThread =
        new Thread(
            () -> {
              try {
                reentrantLockDemo.produce();
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            });
    Thread consumerThread =
        new Thread(
            () -> {
              try {
                reentrantLockDemo.consume();
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            });
    producerThread.start();
    consumerThread.start();
  }

  public void produce() throws InterruptedException {
    lock.lock();
    System.out.println("Producer method ...");
    condition.await();
    lock.unlock();
  }

  public void consume() throws InterruptedException {
    Thread.sleep(2000);
    lock.lock();
    System.out.println("Consumer method ...");
    condition.signal();
    lock.unlock();
  }
}
