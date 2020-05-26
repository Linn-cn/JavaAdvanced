package com.changda.juc.cdl;

import java.util.concurrent.CountDownLatch;

// CountDownLatch 自己实现
public class CDLdemo {

    public static void main(String[] args) throws InterruptedException {
        // 一个请求，后台需要调用多个接口 查询数据
        CountDownLatch cdLdemo = new CountDownLatch(10); // 创建，计数数值
        for (int i = 0; i < 10; i++) { // 启动九个线程，最后一个两秒后启动
            int finalI = i;
            new Thread(() -> {
                // 参与计数
                try {
                    // 等待计数器为0
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("我是" + Thread.currentThread() + ".我执行接口-" + finalI +"调用了");
                // 不影响后续操作
                cdLdemo.countDown();
            }).start();
        }
        cdLdemo.await();

        System.out.println("全部执行完毕.我来召唤神龙");

    }
}
