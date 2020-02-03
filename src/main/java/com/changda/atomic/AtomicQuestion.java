package com.changda.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: JucAndJvm
 * @classname: AtomicQuestion
 * @description:
 * @author: 南街
 * @create: 2020-01-15 21:12
 **/
public class AtomicQuestion {

    public static int actualValue = 3;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(3);
        // if( value == 3){
        //     value = 5
        atomicInteger.compareAndSet(3, 5);
        // 偏离锁 < CAS操作 < 重锁(完全互斥)
        // CAS 操作也是相对重的操作，它也是实现 synchronized 瘦锁(thin lock)的关键
        // 偏离锁就是避免CAS(Compare And Set/Swap)操作
    }

    private synchronized static void compareAndSet(int actualValue, int expectedValue, int newValue) {
        if (actualValue == expectedValue) {
            actualValue = newValue;
        }
    }
}
