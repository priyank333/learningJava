package com.learning.concurrency.volatilekeyword;

public class VolatileDemo {
  // Add volatile keyword to make a work
  public static boolean stop = false;

  public static void main(String args[]) throws InterruptedException {
    Thread testThread =
        new Thread() {
          @Override
          public void run() {
            long i = 1;
            while (!stop) {
              i++;
            }
            System.out.println("Thread stop i=" + i);
          }
        };
    testThread.start();
    Thread.sleep(1000);
    stop = true;
    System.out.println("now, in main thread stop is: " + stop);
    testThread.join();
  }
}
