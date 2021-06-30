package edu.changda.pool.executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: JucAndJvm
 * @classname: ThreadPoolWriteDemo
 * @description: 手写线程池
 * @author: 南街
 * @create: 2019-12-15 17:02
 **/
public class WriteThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                // 线程池中的常驻核心线程数
                2,
                // 线程池中执行的最大线程数，它包含前者
                5,
                // 多余的空闲线程存活时间
                2L,
                // 时间的单位
                TimeUnit.SECONDS,
                // 等待任务队列，即被提交单尚未被执行的任务
                new LinkedBlockingQueue<>(3),
                // 生成线程池中工作线程的线程工厂，一般默认即可
                new MyThreadFactory("writeDemo"),
                // 拒绝策略，使用直接抛出异常
                new ThreadPoolExecutor.AbortPolicy()
        );
        // ExecutorService threadPoolExecutor1 = new ThreadPoolExecutor(
        //         2,
        //         5,
        //         2L,
        //         TimeUnit.SECONDS,
        //         new LinkedBlockingQueue<>(3),
        //         Executors.defaultThreadFactory(),
        //         // 回调给调用者
        //         new ThreadPoolExecutor.CallerRunsPolicy()
        // );
        //
        // ExecutorService threadPoolExecutor2 = new ThreadPoolExecutor(
        //         2,
        //         5,
        //         2L,
        //         TimeUnit.SECONDS,
        //         new LinkedBlockingQueue<>(3),
        //         Executors.defaultThreadFactory(),
        //         // 抛弃队列中等待的最久的
        //         new ThreadPoolExecutor.DiscardOldestPolicy()
        // );
        try {
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
        }
    }
}

/**
 * 自定义线程工厂
 * @author Linn-cn
 * @date 2020/11/16
 */
class MyThreadFactory implements ThreadFactory {


    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public MyThreadFactory(String namePrefix) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        //守护线程
        if (t.isDaemon())
            t.setDaemon(true);
        //线程优先级
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        /**
         * 处理未捕捉的异常
         */
        t.setUncaughtExceptionHandler((t1, e) ->
                System.out.println("do something to handle uncaughtException")
        );
        return t;
    }
}
