package com.learning.concurrency.livelock;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LivelockDemo {
  Lock lock1 = new ReentrantLock();
  Lock lock2 = new ReentrantLock();

  public static void main(String[] args) {
    LivelockDemo livelockDemo = new LivelockDemo();

    Thread thread1 = new Thread(livelockDemo::worker1);
    Thread thread2 = new Thread(livelockDemo::worker2);
    thread1.start();
    thread2.start();
  }

  public void worker1() {
    while (true) {
      try {
        if (lock1.tryLock(50, MILLISECONDS)) {
          System.out.println("Worker1 : acquires : Lock1");
        } else {
          System.out.println("Worker1 : acquires fail : Lock1");
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      try {
        if (lock2.tryLock(50, MILLISECONDS)) {
          System.out.println("Worker1 : acquires : Lock2");
        } else {
          System.out.println("Worker1 : acquires fail : Lock2");
          continue;
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      break;
    }
    lock1.unlock();
    lock2.unlock();
  }

  public void worker2() {
    while (true) {
      try {
        if (lock2.tryLock(50, MILLISECONDS)) {
          System.out.println("Worker2 : acquires : Lock2");
        } else {
          System.out.println("Worker2 : acquires fail : Lock2");
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      try {
        if (lock1.tryLock(50, MILLISECONDS)) {
          System.out.println("Worker2 : acquires : Lock1");
        } else {
          System.out.println("Worker2 : acquires fail : Lock1");
          continue;
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      break;
    }
    lock1.unlock();
    lock2.unlock();
  }
}
