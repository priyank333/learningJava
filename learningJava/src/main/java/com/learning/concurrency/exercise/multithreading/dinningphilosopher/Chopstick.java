package com.learning.concurrency.exercise.multithreading.dinningphilosopher;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    private int id;
    private Lock lock;

    public Chopstick(int id) {
        this.id = id;
        this.lock = new ReentrantLock(true);
    }

    public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException {
        if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println(philosopher + " has picked up chopstick no : " + this.id);
            return true;
        }
        return false;
    }

    public void putDown(Philosopher philosopher, State state) {
        lock.unlock();
        System.out.println(philosopher + " has put down the " + state + " chopstick no : " + this.id);
    }

    @Override
    public String toString() {
        return "Chopstick [id=" + id + ", lock=" + lock + "]";
    }
}
