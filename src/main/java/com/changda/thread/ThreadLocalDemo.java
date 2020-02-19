package com.changda.thread;

/**
 * @program: JvmAndJuc
 * @classname: ThreadLocalDemo
 * @description: ThreadLocal案例
 * @author: 南街
 * @create: 2020-02-12 22:03
 **/
public class ThreadLocalDemo {
    // 一个变量，感觉应该是多线程共享同一个String
    static ThreadLocal<String> value = new ThreadLocal<>();

    public static void main(String[] args) {
        value.set("hello tony");
        System.out.println(Thread.currentThread() + "- (1)获取到value的值为:" + value.get());
        // 开启一个新线程
        new Thread(() -> {
            System.out.println(Thread.currentThread() + "- (2)获取到的value的值为:" + value.get());
        }).start();
    }

}
