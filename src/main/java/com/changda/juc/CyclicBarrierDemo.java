package com.changda.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: JucAndJvm
 * @classname: CyclicBarrierDemo
 * @description: 顺数计数器  CyclicBarrier跟CountDownLatch的区别在于，它可以重复执行
 * @author: 南街
 * @create: 2019-12-06 19:10
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // 每有7个就执行一次barrierAction
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,
                () -> {
                    System.out.println("终结神龙");
                });
        for (int i = 0; i < 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t收集到第:" + tempInt + "颗龙珠");
                try {
                    // 同样是减1操作,但会在这等待，等待满足7个线程的时候在继续往下执行
                    // 相当于CountDownLatch.countDown() + await()
                    cyclicBarrier.await();
                    System.out.println(".....");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
