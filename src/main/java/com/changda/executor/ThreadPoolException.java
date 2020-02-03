package com.changda.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JucAndJvm
 * @classname: ThreadPoolException
 * @description:
 * @author: 南街
 * @create: 2019-12-23 19:55
 **/
public class ThreadPoolException {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(t.getName() + ":" + e.getMessage());
        });
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(() -> {
            throw new RuntimeException("测试线程池捕获");
        });
        threadPool.shutdown();
    }
}
