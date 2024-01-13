package com.learning.concurrency.collection;

import java.util.Random;
import java.util.concurrent.*;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("All worker has completed their tasks.");
            }
        });
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new BarrierWorker(i + 1, random, cyclicBarrier));
        }
        executorService.shutdown();
    }
}

class BarrierWorker implements Runnable {

    public int id;
    public Random random;
    public CyclicBarrier cyclicBarrier;

    public BarrierWorker(int id, Random random, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.random = random;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        doWork();
    }

    public void doWork() {
        try {
            System.out.println("Thread Id : " + this.id + " doing their work.");
            TimeUnit.SECONDS.sleep(random.nextInt(4));
            cyclicBarrier.await();
            System.out.println("Thread Id : " + this.id + " has completed their work.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

}