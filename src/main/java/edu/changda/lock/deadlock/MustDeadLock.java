package edu.changda.lock.deadlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 模拟死锁
 * @author Linn-cn
 * @create 2020/09/23
 */
@Slf4j
public class MustDeadLock implements Runnable {
    public static final Object o1 = new Object();
    public static final Object o2 = new Object();
    public int flag = 1;

    @Override
    public void run() {
        log.info("线程{}的flag为{}", Thread.currentThread().getName(), flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    log.info("线程1获得了两把锁");
                }
            }
        }

        if (flag == 2) {
            synchronized (o2) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    log.info("线程1获得了两把锁");
                }
            }
        }
    }

    public static void main(String[] args) {
        MustDeadLock lock1 = new MustDeadLock();
        MustDeadLock lock2 = new MustDeadLock();
        lock1.flag = 1;
        lock2.flag = 2;

        new Thread(lock1, "t1").start();
        new Thread(lock2, "t2").start();
    }
}
