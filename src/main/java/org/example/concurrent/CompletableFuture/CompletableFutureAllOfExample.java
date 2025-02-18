package org.example.concurrent.CompletableFuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureAllOfExample {
    public static void main(String[] args) throws InterruptedException {
        List<CompletableFuture<String>> futures = new ArrayList<>();

        // 创建多个 CompletableFuture 任务
        for (int i = 0; i < 3; i++) {
            final int taskId = i;
            // 调用 CompletableFuture.supplyAsync 方法后，异步任务会立即开始执行
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    // 模拟不同的耗时操作
                    TimeUnit.SECONDS.sleep(taskId + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskId + " completed");
                return "Task " + taskId + " completed";
            });
            futures.add(future);
        }

        System.out.println("主线程睡眠了");
        Thread.sleep(5000);
        System.out.println("主线程醒了");

        // 使用 allOf 等待所有任务完成，其本身并不会阻塞当前线程
        // 默地跟踪传入的 CompletableFuture 任务状态
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        System.out.println("主线程睡眠了");
        Thread.sleep(5000);
        System.out.println("主线程醒了");
        // 阻塞当前线程，直到所有任务完成
        allFutures.join();

        // 打印所有任务的结果
        for (CompletableFuture<String> future : futures) {
            System.out.println(future.join());
        }
    }
}
