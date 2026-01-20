package com.learning.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("--- CompletableFuture Demo ---");

        basicAsync();
        chaining();
        combining();
        exceptionHandling();
        allOfAnyOf();

        // Sleep to allow async tasks to finish printing before main exits
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }

    private static void basicAsync() throws ExecutionException, InterruptedException {
        System.out.println("\n1. Basic Async:");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        });

        // Block and get the result
        String result = future.get();
        System.out.println("Result: " + result);
    }

    private static void chaining() {
        System.out.println("\n2. Chaining:");
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(String::toUpperCase)
                .thenAccept(result -> System.out.println("Chained Result: " + result));
    }

    private static void combining() throws ExecutionException, InterruptedException {
        System.out.println("\n3. Combining:");

        // thenCompose (Dependent)
        CompletableFuture<String> composed = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
        System.out.println("Composed: " + composed.get());

        // thenCombine (Independent)
        CompletableFuture<String> combined = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);
        System.out.println("Combined: " + combined.get());
    }

    private static void exceptionHandling() throws ExecutionException, InterruptedException {
        System.out.println("\n4. Exception Handling:");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Computation error!");
            }
            return "Success";
        }).exceptionally(ex -> {
            System.out.println("Exception occurred: " + ex.getMessage());
            return "Default Value";
        });

        System.out.println("Result after exception: " + future.get());

        // handle() allows access to both result and exception
        CompletableFuture<String> handled = CompletableFuture.<String>supplyAsync(() -> {
            throw new RuntimeException("Error!");
        }).handle((res, ex) -> {
            if (ex != null) {
                return "Recovered from " + ex.getMessage();
            }
            return res;
        });
        System.out.println("Handled result: " + handled.get());
    }

    private static void allOfAnyOf() throws ExecutionException, InterruptedException {
        System.out.println("\n5. AllOf / AnyOf:");
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Future 1");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "Future 2");
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "Future 3");

        CompletableFuture<Void> allOf = CompletableFuture.allOf(f1, f2, f3);
        allOf.get(); // Wait for all
        System.out.println("All futures completed.");

        System.out.println("F1: " + f1.get());
        System.out.println("F2: " + f2.get());
        System.out.println("F3: " + f3.get());
    }
}
