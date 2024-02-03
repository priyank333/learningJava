package com.learning.concurrency.exercise.multithreading.popcorneating;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class PopcornPacket {
  private int packetId;
  private Semaphore semaphore;
  private AtomicInteger packetSize;

  public PopcornPacket(int packetId, int permits, int packetSize) {
    this.packetId = packetId;
    this.packetSize = new AtomicInteger(packetSize);
    semaphore = new Semaphore(permits);
  }

  public void consume(Person person) {
    try {
      if (packetSize.get() > 0) {
        if (semaphore.tryAcquire(500, TimeUnit.MILLISECONDS)) {
          packetSize.getAndDecrement();
          System.out.println(person + " has started to consume packet : " + this);
          TimeUnit.SECONDS.sleep(4);
        } else {
          System.out.println(this + " failed to acquire.");
        }
      } else {
        System.out.println(this + " packet is empty.");
      }
    } catch (InterruptedException interruptedException) {

    } finally {
      semaphore.release();
      System.out.println(person + " has finished consume packet : " + this);
    }
  }

  @Override
  public String toString() {
    return "PacketId=" + packetId;
  }
}
