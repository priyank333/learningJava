package com.learning.concurrency.collection;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
  public static void main(String[] args) {
    List<Integer> list = new CopyOnWriteArrayList<>(new Integer[] {1, 2, 3});
    Iterator<Integer> prevIter = list.listIterator();
    list.add(4);
    prevIter.forEachRemaining(System.out::println);
  }
}
