package edu.changda.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description  CAS 是什么？ CompareAndSwap
 * 比较并交换
 * @author Linn-cn
 * @create 2020/8/19
 */
public class AtomicIntegerDemo {

    public static int actualValue = 3;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(3);
        // if( value == 3){
        //     value = 5
        System.out.println(atomicInteger.compareAndSet(3, 5));
        System.out.println(atomicInteger.compareAndSet(5,1024));
        System.out.println(atomicInteger.get());
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
