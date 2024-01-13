package com.learning.concurrency.exercise.multithreading.dinningphilosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philosopher[] philosophers = null;
        Chopstick[] chopsticks = null;

        philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
        chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];

        for (int iter = 0; iter < Constants.NUMBER_OF_CHOPSTICKS; iter++) {
            chopsticks[iter] = new Chopstick(iter + 1);
        }

        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int chopstickCnt = 0;
        for (int iter = 0; iter < Constants.NUMBER_OF_PHILOSOPHERS; iter++) {
            philosophers[iter] = new Philosopher(iter + 1, chopsticks[chopstickCnt],
                    chopsticks[(chopstickCnt + 1) % Constants.NUMBER_OF_CHOPSTICKS]);
            chopstickCnt = (chopstickCnt + 1) % Constants.NUMBER_OF_CHOPSTICKS;
            executorService.execute(philosophers[iter]);
        }

        TimeUnit.MILLISECONDS.sleep(Constants.SIMULATION_RUNNING_TIME);
        for (Philosopher philosopher : philosophers) {
            philosopher.setFull(true);
        }

        executorService.shutdown();
        while (!executorService.isTerminated())
            TimeUnit.MILLISECONDS.sleep(1000);
        for (Philosopher philosopher : philosophers) {
            System.out.println(philosopher + ": eating# : " + philosopher.getEatingCounter());

        }
    }
}
