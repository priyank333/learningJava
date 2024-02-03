package com.learning.concurrency.waitnotify;

public class WaitAndNotify {
  public static void main(String[] args) {
    Task task = new Task();
    Thread t1 =
        new Thread(
            () -> {
              task.waiting();
            });

    Thread t2 =
        new Thread(
            () -> {
              task.unlockTask();
            });
    t1.start();
    t2.start();
  }
}

class Task {
  private boolean lock = true;

  public void waiting() {
    while (lock) {
      try {
        synchronized (this) {
          System.out.println("Waiting to unlock a task ...");
          wait();
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void unlockTask() {
    synchronized (this) {
      System.out.println("Unlocking a task ...");
      lock = false;
      notify();
    }
  }
}
