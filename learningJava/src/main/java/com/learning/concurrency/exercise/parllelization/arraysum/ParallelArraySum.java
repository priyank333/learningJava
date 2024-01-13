package com.learning.concurrency.exercise.parllelization.arraysum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ParallelArraySum extends ArraySum {

    private final int NUMBER_OF_THREADS;

    public ParallelArraySum(int[] arr, int numOfThreads) {
        super(arr);
        this.NUMBER_OF_THREADS = numOfThreads;
    }

    private Callable<Long> createJob(int low, int high) {
        return new Callable<Long>() {
            @Override
            public Long call() {
                return findSubArrSum(low, high);
            }
        };
    }

    private long findSubArrSum(int low, int high) {
        long sum = 0;
        for (int iter = low; iter < high; iter++) {
            sum += arr[iter];
        }

        return sum;
    }

    @Override
    public void findSum() {
        try {
            startProcess();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void startProcess() throws InterruptedException, ExecutionException {
        int size = (int) Math.ceil(arr.length * 1 / NUMBER_OF_THREADS);

        size = size < 1 ? 1 : size;
        List<FutureTask<Long>> futureTasks = new ArrayList<>();
        int unprocessedSize = arr.length;
        for (int iter = 0; iter < NUMBER_OF_THREADS; iter++) {

            Callable<Long> job = null;
            if (unprocessedSize - ((iter + 1) * size) < size) {
                job = createJob(iter * size, arr.length);

                // Exit from loop
                iter = NUMBER_OF_THREADS;
            } else {
                job = createJob(iter * size, (iter + 1) * size);
            }
            unprocessedSize = unprocessedSize - (iter + 1) * size;
            FutureTask<Long> futureTask = new FutureTask<>(job);
            futureTasks.add(futureTask);
            Thread thread = new Thread(futureTask);
            thread.start();
        }

        long sum = 0;
        for (FutureTask<Long> futureTask : futureTasks) {
            sum += futureTask.get();
        }
        this.ans = sum;

    }

}
