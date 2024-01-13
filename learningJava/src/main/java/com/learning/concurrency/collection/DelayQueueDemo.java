package com.learning.concurrency.collection;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {
    public static void main(String[] args) {
        DelayQueue<DelayWorker> delayQueue = new DelayQueue<>();
        Random random = new Random();
        int dataSize = 300;

        for (int i = 0; i < dataSize; i++) {
            delayQueue.add(new DelayWorker(random.nextInt(50000), "Message : " + (i + 1)));
        }

        while (!delayQueue.isEmpty()) {
            try {
                DelayWorker receivedMsg = delayQueue.take();

                System.out.println("Received Message : " + receivedMsg);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class DelayWorker implements Delayed {

    private long delay;
    private String message;

    public DelayWorker(int delay, String message) {
        this.delay = System.currentTimeMillis() + delay;
        this.message = message;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delay - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.delay < ((DelayWorker) o).getDelay()) return -1;
        else if (this.delay > ((DelayWorker) o).getDelay()) return +1;
        return 0;
    }

    @Override
    public String toString() {
        return "DelayWorker{" +
                "delay=" + delay +
                ", message='" + message + '\'' +
                '}';
    }
}
