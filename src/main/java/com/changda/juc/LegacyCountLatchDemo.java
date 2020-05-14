package com.changda.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JucAndJvm
 * @classname: LegacyCountLatch
 * @description:
 * @author: 南街
 * @create: 2020-01-15 13:31
 **/
public class LegacyCountLatchDemo {

//    public static final Logger logger = Logger.getLogger(LegacyCountLatchDemo.class.getSimpleName());

    public static void main(String[] args) throws InterruptedException {
        // 倒计数5
        MyCountDownLatch latch = new MyCountDownLatch(5);

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 4; i++) {
            threadPool.execute(() -> {
                action();
                latch.countDown();
            });
        }

        latch.await();
        System.out.println("Done");

        threadPool.shutdown();
    }

    private static void action() {
        System.out.printf("线程[%s] 正在执行....\n", Thread.currentThread().getName());
    }

    /**
     * Java 1.5+ 实现
     */
    private static class MyCountDownLatch {

        private int count;

        private final Lock lock = new ReentrantLock();

        private final Condition condition = lock.newCondition();

        public MyCountDownLatch(int count) {
            this.count = count;
        }

        public void countDown() {
            lock.lock();
            try {
                if (count < 1) {
                    return;
                }
                count--;
                if (count < 1) {    //当数量减少至0时，唤起被阻塞的线程
                    condition.signalAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void await() throws InterruptedException {
            // 当count > 0 等待
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            lock.lock();
            try {
                while (count > 0) {
                    condition.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }


    /**
     * Java 1.4 实现
     */
    private static class LegacyCountDownLatch {

        private int count;

        public LegacyCountDownLatch(int count) {
            this.count = count;
        }

        public void countDown() {
            synchronized (this) {
                if (count < 1) {
                    return;
                }
                count--;
                if (count < 1) {    //当数量减少至0时，唤起被阻塞的线程
                    notifyAll();
                }
            }
        }

        public void await() throws InterruptedException {
            // 当count > 0 等待
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            synchronized (this) {
                while (count > 0) {
                    wait(); // 阻塞当前线程
                }
            }
        }

    }
}
