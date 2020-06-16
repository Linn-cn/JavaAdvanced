package com.changda.pool.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: JucAndJvm
 * @classname: MyThreadPoolDemo
 * @description:
 * @author: 南街
 * @create: 2019-12-10 22:20
 **/
public class ExecutorsDemo {
    public static void main(String[] args) {
        // 一池5个受理线程，类似一个银行有5个受理窗口
        /**
         * FixedThreadPool是固定核心线程的线程池，固定核心线程数由用户传入
         *
         * corePoolSize => nThreads，核心线程池的数量为1
         * maximumPoolSize => nThreads，线程池最大数量为nThreads，即最多只可以创建nThreads个线程
         * keepAliveTime => 0L
         * unit => 毫秒
         * workQueue => LinkedBlockingQueue
         * 它和SingleThreadExecutor类似，唯一的区别就是核心线程数不同，并且由于使用的是LinkedBlockingQueue，在资源有限的时候容易引起OOM异常
         */
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 一池1个工作线程，类似一个银行有1个受理窗口
        // 当它不在被引用，它会被GC -> finalize() -> shutdown()
        /**
         * corePoolSize => 1，核心线程池的数量为1
         * maximumPoolSize => 1，线程池最大数量为1，即最多只可以创建一个线程，唯一的线程就是核心线程
         * keepAliveTime => 0L
         * unit => 毫秒
         * workQueue => LinkedBlockingQueue
         */
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
        // 一池N个工作线程，类似一个银行有N个受理窗口
        /**
         * CachedThreadPool是一个根据需要创建新线程的线程池
         *
         * corePoolSize => 0，核心线程池的数量为0
         * maximumPoolSize => Integer.MAX_VALUE，线程池最大数量为Integer.MAX_VALUE，可以认为可以无限创建线程
         * keepAliveTime => 60L
         * unit => 秒
         * workQueue => SynchronousQueue
         *
         */
        ExecutorService threadPool2 = Executors.newCachedThreadPool();

        /**
         * 一个拥有多个任务队列的线程池，可以减少连接数，创建当前可用cpu数量的线程来并行执行。
         */
        ExecutorService executorService = Executors.newWorkStealingPool();

        /**
         * newSingleThreadScheduledExecutor() 和 newScheduledThreadPool(int corePoolSize)，
         * 创建的是个 ScheduledExecutorService，可以进行定时或周期性的工作调度，区别在于单一工作线程还是多个工作线程。
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
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
