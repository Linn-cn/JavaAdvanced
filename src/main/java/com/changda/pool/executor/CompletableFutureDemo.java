package com.changda.pool.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: JucAndJvm
 * @classname: CompletableFutureDemo
 * @description:
 * @author: 南街
 * @create: 2019-12-17 19:42
 **/
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + "没有返回,update mysql ok");
//        });
//        System.out.println(future.get());
        // 异步回调
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回,update mysql ok");
            int age = 10/0;
            return 1024;
        });
        Integer integer = supplyAsync.whenComplete((t, u) -> {
            System.out.println("*******t" + t);
            System.out.println("*******u" + u);
        }).exceptionally(f -> {
            System.out.println("*******excption" + f.getMessage());
            return 4444;
        }).get();
        System.out.println(integer);
    }
}
