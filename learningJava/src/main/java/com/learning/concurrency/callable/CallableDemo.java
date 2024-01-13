package com.learning.concurrency.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        int nTask = 10;

        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < nTask; i++) {
            Future<String> future = executorService.submit(new CallableTask(i + 1));
            futures.add(future);
        }
        for (Future future : futures) {
            try {
                System.out.println(String.valueOf(future.get()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
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
        System.out.println();
    }
}

class CallableTask implements Callable<String> {

    private int id;

    public CallableTask(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        return String.valueOf(this.id);
    }
}
