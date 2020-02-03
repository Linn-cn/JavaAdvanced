package com.changda.executor;

import java.util.concurrent.*;

/**
 * @program: JucAndJvm
 * @classname: ThreadPoolWriteDemo
 * @description: 手写线程池
 * @author: 南街
 * @create: 2019-12-15 17:02
 **/
public class ThreadPoolWriteDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                // 直接抛出异常
                new ThreadPoolExecutor.AbortPolicy()
        );

        ExecutorService threadPoolExecutor1 = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                // 回调给调用者
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        ExecutorService threadPoolExecutor2 = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                // 抛弃队列中等待的最久的
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        try {
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor1.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPoolExecutor1.shutdown();
        }
    }
}
