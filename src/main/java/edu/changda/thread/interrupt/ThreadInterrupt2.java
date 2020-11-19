package edu.changda.thread.interrupt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname ThreadInterrupt
 * @description 一道面试题:实现一个容器，提供两个方法，add, size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，
 * 当个数到5个时，线程2给出提示并结束线程1
 *
 * 用countDownLatch解决
 * @create 2020-02-27 11:03
 **/
public class ThreadInterrupt2 {

    public static void main(String[] args) throws InterruptedException {
        Container container = new Container();
        CountDownLatch latch = new CountDownLatch(1);

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            for (int i = 0; i < 10; i++) {
                if (container.size() == 5) {
                    latch.countDown();
                }
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("检查到中断");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "准备add:" + i);
                container.add(i);
            }

        }, "thread1");
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            while (true) {
                System.out.println("watch:" + container.size());
                if (container.size() >= 5) {
                    thread1.interrupt();
                    System.out.println("试图中断thread1");
                    break;
                }
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
