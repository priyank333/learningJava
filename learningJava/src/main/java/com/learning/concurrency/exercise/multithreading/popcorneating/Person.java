package com.learning.concurrency.exercise.multithreading.popcorneating;

import java.util.Random;

public class Person implements Runnable {
  private int personId;
  private PopcornPacket[] popcornPackets;
  private Random random;
  private int personCapacity;

  public Person(int personId, PopcornPacket[] popcornPackets, int personCapacity) {
    this.personId = personId;
    this.popcornPackets = popcornPackets;
    this.random = new Random();
    this.personCapacity = personCapacity;
  }

  @Override
  public void run() {

    while (personCapacity > 0) {
      PopcornPacket currentPacket = popcornPackets[random.nextInt(popcornPackets.length)];
      currentPacket.consume(this);
      personCapacity--;
    }
  }

  @Override
  public String toString() {
    return "personId=" + personId;
  }
}
