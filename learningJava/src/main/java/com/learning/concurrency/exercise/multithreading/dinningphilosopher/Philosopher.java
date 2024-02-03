package com.learning.concurrency.exercise.multithreading.dinningphilosopher;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
  private int id;
  private boolean full;
  private Chopstick leftChopstick;
  private Chopstick rightChopstick;
  private Random random;
  private int eatingCounter;

  public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
    this.id = id;
    this.leftChopstick = leftChopstick;
    this.rightChopstick = rightChopstick;
    random = new Random();
    eatingCounter = 0;
  }

  public void think() throws InterruptedException {
    System.out.println(this + " is thinking ...");
    TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
  }

  public void eat() throws InterruptedException {
    System.out.println(this + " is eating ...");
    TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
  }

  public boolean getFull() {
    return this.full;
  }

  public void setFull(boolean full) {
    this.full = full;
  }

  @Override
  public void run() {
    try {
      while (!full) {
        think();

        if (leftChopstick.pickUp(this, State.LEFT) && rightChopstick.pickUp(this, State.RIGHT)) {
          eat();
          leftChopstick.putDown(this, State.LEFT);
          rightChopstick.putDown(this, State.RIGHT);
          eatingCounter++;
        }
      }
    } catch (Exception exception) {
      System.err.println("Exception occured : " + exception);
    }
  }

  @Override
  public String toString() {
    return "Philosopher [id=" + id + "]";
  }

  public int getEatingCounter() {
    return eatingCounter;
  }

  public void setEatingCounter(int eatingCounter) {
    this.eatingCounter = eatingCounter;
  }
}
