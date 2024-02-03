package com.learning.concurrency.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockDemo {
  public Lock lock1 = new ReentrantLock();
  public Lock lock2 = new ReentrantLock();

  public static void main(String[] args) {
    DeadlockDemo deadlockDemo = new DeadlockDemo();
    Thread thread1 =
        new Thread(
            () -> {
              deadlockDemo.worker1();
            });
    Thread thread2 =
        new Thread(
            () -> {
              deadlockDemo.worker2();
            });
    thread1.start();
    thread2.start();
  }

  public void worker1() {
    lock1.lock();
    System.out.println("Lock1 is locked : Worker1");
    try {
      Thread.sleep(300);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    lock2.lock();
    System.out.println("Lock2 is locked : Worker1");
    lock1.unlock();
    lock2.unlock();
  }

  public void worker2() {
    lock2.lock();
    System.out.println("Lock2 is locked : Worker2");
    try {
      Thread.sleep(300);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    lock1.lock();
    System.out.println("Lock1 is locked : Worker2");
    lock1.unlock();
    lock2.unlock();
  }
}
