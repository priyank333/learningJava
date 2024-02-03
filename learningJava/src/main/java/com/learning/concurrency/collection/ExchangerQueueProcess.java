package com.learning.concurrency.collection;

import static java.util.concurrent.CompletableFuture.runAsync;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerQueueProcess {
  private static final int BUFFER_SIZE = 10;

  public static void main(String[] args) {
    Exchanger<Queue<String>> readerExchanger = new Exchanger<>();
    Exchanger<Queue<String>> writeExchanger = new Exchanger<>();

    Runnable reader =
        () -> {
          Queue<String> readerBuffer = new ConcurrentLinkedQueue<>();
          while (true) {
            readerBuffer.add(UUID.randomUUID().toString());
            if (readerBuffer.size() >= BUFFER_SIZE) {
              try {
                readerBuffer = readerExchanger.exchange(readerBuffer);
                System.out.println("Exchange data from reader");
                TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            }
          }
        };

    Runnable processor =
        () -> {
          Queue<String> processorBuffer = new ConcurrentLinkedQueue<>();
          Queue<String> writeBuffer = new ConcurrentLinkedQueue<>();
          try {
            processorBuffer = readerExchanger.exchange(processorBuffer);
            System.out.println("Exchange data from processor");
            TimeUnit.SECONDS.sleep(1);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          while (true) {
            writeBuffer.add(processorBuffer.poll());
            if (processorBuffer.isEmpty()) {
              try {
                processorBuffer = readerExchanger.exchange(processorBuffer);
                System.out.println("Exchange data from processor to reader");
                TimeUnit.SECONDS.sleep(1);
                writeBuffer = writeExchanger.exchange(writeBuffer);
                System.out.println("Exchange data from processor to writer");
                TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            }
          }
        };

    Runnable writer =
        () -> {
          Queue<String> writerBuffer = new ConcurrentLinkedQueue<>();

          try {
            writerBuffer = writeExchanger.exchange(writerBuffer);
            while (true) {
              System.out.println("Writing processed data : " + writerBuffer.poll());

              if (writerBuffer.isEmpty()) {
                writerBuffer = writeExchanger.exchange(writerBuffer);
                System.out.println("Exchange data from writer to processor");
                TimeUnit.SECONDS.sleep(1);
              }
            }
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        };

    CompletableFuture.allOf(runAsync(reader), runAsync(processor), runAsync(writer)).join();
  }
}
