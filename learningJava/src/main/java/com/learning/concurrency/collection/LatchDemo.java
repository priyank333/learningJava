package com.learning.concurrency.collection;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LatchDemo {

    private static final int LATCH_COUNT = 4;
    private static final int WORKER_COUNT = 4;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(LATCH_COUNT);
        AtomicInteger workerCount = new AtomicInteger(0);
        List<Thread> tasks = Stream.generate(() -> new Thread(new LatchTask(1000, countDownLatch),
                        "Worker-" + workerCount.addAndGet(1))).limit(WORKER_COUNT).
                collect(Collectors.toList());
        tasks.forEach(Thread::start);
        countDownLatch.await();
        System.out.println("Main method is executed.");
        System.out.println("CountDown Count : " + countDownLatch.getCount());
    }
}

class LatchTask implements Runnable {

    private int delay;
    private CountDownLatch countDownLatch;

    public LatchTask(int delay, CountDownLatch countDownLatch) {
        this.delay = delay;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " has started their task.");
            Thread.sleep(delay);
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " has finished the task.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
