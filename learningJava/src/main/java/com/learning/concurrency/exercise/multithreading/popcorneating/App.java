package com.learning.concurrency.exercise.multithreading.popcorneating;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        PopcornPacket[] popcornPackets = new PopcornPacket[Constants.PACKET_NUMBER];
        for (int iter = 0; iter < popcornPackets.length; iter++) {
            popcornPackets[iter] = new PopcornPacket(iter + 1,
                    Constants.AT_A_TIME_PACKET_CONSUME_SIZE, Constants.PACKET_SIZE);
        }

        Person[] persons = new Person[Constants.PERSON_NUMBER];

        for (int iter = 0; iter < persons.length; iter++) {
            persons[iter] = new Person(iter + 1, popcornPackets, Constants.PERSON_CONSUME_SIZE);
            executorService.execute(persons[iter]);
        }

        Thread.sleep(Constants.SIMULATION_TIME_MS);
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(2000);
        }
    }
}
