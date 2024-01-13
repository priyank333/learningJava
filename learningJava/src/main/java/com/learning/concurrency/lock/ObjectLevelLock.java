package com.learning.concurrency.lock;

public class ObjectLevelLock {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ObjectLevelLockTask(), "T1");
        Thread t2 = new Thread(new ObjectLevelLockTask(), "T2");
        Thread t3 = new Thread(new ObjectLevelLockTask(), "T3");
        t1.start();
        t2.start();
        t3.start();
    }

}

class ObjectLevelLockTask implements Runnable {
    @Override
    public void run() {
        //Object level locking. Each object acquires a lock.
        synchronized (this) {
            System.out.println("Thread :: " + Thread.currentThread().getName() + " running ...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread :: " + Thread.currentThread().getName() + " end.");
        }
    }
}