package com.learning.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableFutureDemo {

    public static void main(String[] args) {
        System.out.println("--- Callable and Future Demo ---");

        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 1. Define a Callable task
        Callable<String> longRunningTask = () -> {
            System.out.println("Task started...");
            Thread.sleep(2000); // Simulate work
            return "Task Completed Result";
        };

        // 2. Submit the task to ExecutorService
        System.out.println("Submitting task...");
        Future<String> future = executor.submit(longRunningTask);

        // 3. Check if done (should be false initially)
        System.out.println("Is task done? " + future.isDone());

        try {
            // 4. Get the result (blocking)
            System.out.println("Waiting for result...");
            String result = future.get(3, TimeUnit.SECONDS); // Good practice to use timeout
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println("Is task done? " + future.isDone());

        // 5. Demonstrate cancel
        Future<String> futureToCancel = executor.submit(() -> {
            Thread.sleep(5000);
            return "Should not happen";
        });

        System.out.println("\nSubmitting another task to cancel...");
        try {
            Thread.sleep(100); // Give it a moment to start
            boolean cancelled = futureToCancel.cancel(true); // May interrupt if running
            System.out.println("Task cancelled? " + cancelled);
            System.out.println("Is cancelled task done? " + futureToCancel.isDone());
            System.out.println("Is cancelled task cancelled? " + futureToCancel.isCancelled());
            
            // This would throw CancellationException
            // futureToCancel.get(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
