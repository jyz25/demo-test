package org.example.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExecutionTest {
    public static void main(String[] args) {
        System.out.println("Before supplyAsync call");
        // 调用 CompletableFuture.supplyAsync 方法后，异步任务会立即开始执行
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Async task started");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Async task completed");
            return "Task result";
        });
        System.out.println("After supplyAsync call");

        try {
            // 等待异步任务完成并获取结果
            String result = future.get();
            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
