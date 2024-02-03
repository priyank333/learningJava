package com.learning.concurrency.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedList {
  public static void main(String[] args) throws InterruptedException {

    // This is inefficient, because it uses intrinsic lock.
    // Instead of use concurrentList
    List<Integer> list = Collections.synchronizedList(new ArrayList<>());

    Thread t1 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                for (int i = 0; i < 1000; i++) {
                  list.add(i);
                }
              }
            });

    Thread t2 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                for (int i = 0; i < 1000; i++) {
                  list.add(i);
                }
              }
            });

    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println("Size of list : " + list.size());
  }
}
