package com.changda.thread.interrupt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname ThreadInterrupt
 * @description 一道面试题:实现一个容器，提供两个方法，add, size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，
 * 当个数到5个时，线程2给出提示并结束线程2
 *
 * 用countDownLatch解决
 * @create 2020-02-27 11:03
 **/
public class ThreadInterrupt3 {

    public static void main(String[] args) throws InterruptedException {
        Container container = new Container();
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println("中断标志:" + Thread.currentThread().isInterrupted());
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("检查到中断");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "准备add:" + i);
                container.add(i);
                if (container.size() == 5) {
                    condition1.signal();
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            lock.unlock();

        }, "thread1");
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            lock.lock();
            while (true) {
                System.out.println("watch:" + container.size());
                if (container.size() >= 5) {
                    thread1.interrupt();
                    System.out.println("试图中断thread1");
                    condition2.signal();
                    break;
                }
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }, "thread2");
        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        thread1.start();
    }

    static class Container {
        volatile List<Integer> list = new ArrayList<>();

        public void add(Integer value) {
            list.add(value);
        }

        public int size() {
            return list.size();
        }
    }
}
