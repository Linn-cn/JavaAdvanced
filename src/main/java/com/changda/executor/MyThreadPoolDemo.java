package com.changda.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: JucAndJvm
 * @classname: MyThreadPoolDemo
 * @description:
 * @author: 南街
 * @create: 2019-12-10 22:20
 **/
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        // 一池5个受理线程，类似一个银行有5个受理窗口
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 一池1个工作线程，类似一个银行有1个受理窗口
        // 当它不在被引用，它会被GC -> finalize() -> shutdown()
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
        // 一池N个工作线程，类似一个银行有N个受理窗口
        ExecutorService threadPool2 = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
