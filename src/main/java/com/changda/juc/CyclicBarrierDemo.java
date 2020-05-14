package com.changda.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: JucAndJvm
 * @classname: CyclicBarrierDemo
 * @description: 顺数计数器
 * @author: 南街
 * @create: 2019-12-06 19:10
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,
                () -> {
                    System.out.println("终结神龙");
                });
        for (int i = 0; i < 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t收集到第:" + tempInt + "颗龙珠");
                try {
                    // 同样是减1操作
                    // 相当于CountDownLatch.countDown() + await()
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
