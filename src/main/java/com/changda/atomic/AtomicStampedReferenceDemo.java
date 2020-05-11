package com.changda.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname AtomicStampedReferenceDemo
 * @description 版本号原子引用，可用于解决ABA问题
 * @create 2020-03-23 13:10
 **/
public class AtomicStampedReferenceDemo {

    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    private static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<Integer>(100,1);;

    public static void main(String[] args) {
        System.out.println("==========以下是ABA问题的产生==============");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();
        Thread t2 = new Thread(() -> {
            // 暂停一秒钟t2线程，保证上面的t1线程完成一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(atomicReference.compareAndSet(100, 200) + "\t" + atomicReference.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        t2.start();
        while (t2.isAlive()) {

        }
        System.out.println("==========以下是ABA问题的解决==============");

        new Thread(() -> {
            System.out.println("第一次版本号stamp = " + stampedReference.getStamp());
            // 暂停3秒钟t3线程
            try {
                TimeUnit.SECONDS.sleep(3);
                stampedReference.compareAndSet(100, 101, stampedReference.getStamp(),
                        stampedReference.getStamp() + 1);
                System.out.println("第二次版本号stamp = " + stampedReference.getStamp());
                stampedReference.compareAndSet(101, 100, stampedReference.getStamp(),
                        stampedReference.getStamp() + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第三次版本号stamp = " + stampedReference.getStamp());
        },"t3").start();
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println("t4第一次版本号stamp = " + stamp);
            // 暂停5秒钟t4线程
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t4修改:" + stampedReference.compareAndSet(100, 2019, stamp,
                        stamp + 1));
                System.out.println("stampedReference.getReference() = " + stampedReference.getReference());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t4").start();
    }
}
