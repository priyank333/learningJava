package com.learning.concurrency.lock;

public class ClassLevelLock {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ClassLevelLockTask(), "T1");
        Thread t2 = new Thread(new ClassLevelLockTask(), "T2");
        Thread t3 = new Thread(new ClassLevelLockTask(), "T3");
        t1.start();
        t2.start();
        t3.start();
    }
}

class ClassLevelLockTask implements Runnable {
    @Override
    public void run() {
        //Class level locking. Single lock acquire for a class.
        synchronized (ClassLevelLockTask.class) {
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