package com.learning.concurrency.exercise.multithreading.seqprint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SequencePrint {
    public static void main(String[] args) throws InterruptedException {
        Task task1 = new Task("Printer 1");
        Task task2 = new Task("Printer 2");
        Task task3 = new Task("Printer 3");
        Printer printer = new Printer();
        printer.printer1(task1);
        printer.printer2(task2);
        printer.printer3(task3);
    }
}

class Task implements Runnable {
    public String printer;

    public Task(String printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        System.out.println("Printer : " + printer);
    }
}

class Printer {
    public int i = 0;
    public ReentrantLock lock = new ReentrantLock();

    public Condition condition = lock.newCondition();

    public void printer1(Runnable runnable) throws InterruptedException {
        lock.lock();
        runnable.run();
        i = 1;
        condition.signalAll();
        lock.unlock();
    }

    public void printer2(Runnable runnable) throws InterruptedException {

        lock.lock();
        while (i < 1) {
            condition.await();
        }
        runnable.run();
        i = 2;
        condition.signalAll();
        lock.unlock();
    }

    public void printer3(Runnable runnable) throws InterruptedException {
        lock.lock();
        while (i < 2) {
            condition.await();
        }
        runnable.run();
        lock.unlock();
    }
}
