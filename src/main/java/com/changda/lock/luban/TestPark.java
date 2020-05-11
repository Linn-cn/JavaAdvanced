package com.changda.lock.luban;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JucAndJvm
 * @classname: Test
 * @description:
 * @author: 南街
 * @create: 2020-01-26 15:32
 **/
public class TestPark {
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            testsync();
        },"t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("main");
            LockSupport.unpark(t1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testsync(){
        System.out.println(Thread.currentThread().getName());
        LockSupport.park();
    }
}
