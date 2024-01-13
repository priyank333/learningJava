package com.learning.concurrency.forkjoin.recursiveaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {
        int N = 700;
        List<Integer> list = getRandomList(N);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ProcessListAction action = new ProcessListAction(list);
        forkJoinPool.invoke(action);
    }

    private static List<Integer> getRandomList(int n) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int iter = 0; iter < n; iter++) {
            list.add(random.nextInt(n));
        }
        return list;
    }
}
