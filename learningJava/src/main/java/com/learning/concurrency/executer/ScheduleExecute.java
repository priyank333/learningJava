package com.learning.concurrency.executer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecute {
  public static void main(String[] args) {
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    // Submits a periodic action that becomes enabled first after the given initial delay,
    // and subsequently with the given period; that is, executions will commence after initialDelay,
    // then initialDelay + period, then initialDelay + 2 * period, and so on.
    scheduledExecutorService.scheduleAtFixedRate(
        new ScheduleTask(), 1000, 1, TimeUnit.MILLISECONDS);
    // scheduledExecutorService.shutdown();
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                new Runnable() {
                  @Override
                  public void run() {
                    awaitTerminationAfterShutdown(scheduledExecutorService);
                  }
                }));
  }

  public static void awaitTerminationAfterShutdown(ExecutorService executorService) {
    System.out.println("Shutting down ...");
    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(100, TimeUnit.SECONDS)) {
        List<Runnable> droppedTasks = executorService.shutdownNow();
        System.out.println("Total droppedTask : " + droppedTasks.size());
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}

class ScheduleTask implements Runnable {
  @Override
  public void run() {
    System.out.println(
        "Thread : " + Thread.currentThread().getName() + " downloading data from the web ...");
  }
}
