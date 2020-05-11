package com.changda.thread.executor;

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
                2,  // 线程池中的常驻核心线程数
                5,  // 线程池中执行的最大线程数，它包含前者
                2L, // 多余的空闲线程存活时间
                TimeUnit.SECONDS,   // 时间的单位
                new LinkedBlockingQueue<>(3),   // 等待任务队列，即被提交单尚未被执行的任务
                Executors.defaultThreadFactory(),   // 生成线程池中工作线程的线程工厂，一般默认即可
                // 直接抛出异常
                new ThreadPoolExecutor.AbortPolicy()    // 拒绝策略
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
