package com.changda.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname CASDemo
 * @description
 * CAS 是什么？ CompareAndSwap
 * 比较并交换
 *
 * @create 2020-03-22 11:11
 **/
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,2019));
        System.out.println(atomicInteger.compareAndSet(5,1024));
    }
}
