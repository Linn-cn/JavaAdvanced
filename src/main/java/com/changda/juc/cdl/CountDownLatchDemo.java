package com.changda.juc.cdl;

import java.util.concurrent.CountDownLatch;

/**
 * @program: JucAndJvm
 * @classname: CountDownLatchDemo
 * @description: 倒数计数器
 * @author: 南街
 * @create: 2019-11-24 16:57
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 倒数技术 6
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开教室");
                countDownLatch.countDown(); // 减1操作
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t班长关门走人");
    }

    private static void closeDoor() {
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开教室");
            },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + "\t班长关门走人");
    }
}
