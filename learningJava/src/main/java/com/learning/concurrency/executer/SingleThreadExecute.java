package com.learning.concurrency.executer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecute {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        int nTask = 5;
        for (int i = 0; i < nTask; i++) {
            executorService.execute(new SingleThreadTask(i));
        }
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                awaitTerminationAfterShutdown(executorService);
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

class SingleThreadTask implements Runnable {
    private int id;

    public SingleThreadTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("ThreadName : " + Thread.currentThread().getName() + ", ThreadId : " + this.id + " is processing ...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
