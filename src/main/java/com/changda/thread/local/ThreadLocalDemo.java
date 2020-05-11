package com.changda.thread.local;

/**
 * @program: JvmAndJuc
 * @classname: ThreadLocalDemo
 * @description: ThreadLocal 线程局部变量 案例
 * @author: 南街
 * @create: 2020-02-12 22:03
 **/
public class ThreadLocalDemo {
    static ThreadLocal<String> value = new ThreadLocal<>();

    public static void main(String[] args) {
        // 每个线程都有一个threadLocals属性，用来存放ThreadLocalMap引用
        // ThreadLocalMap是一种类似HashMap的散列表结构，初始化大小也为16.但对于哈希冲突的解决是寻找下一个空位(开放定址法)
        // set的时候会判断当前线程的threadLocals是不是null，不是的话就set，否则就创建
        // set的时候，以当前ThreadLocal对象为key，值为value
        value.set("hello tony");
        System.out.println(Thread.currentThread() + "- (1)获取到value的值为:" + value.get());
        // 开启一个新线程
        new Thread(() -> {
            System.out.println(Thread.currentThread() + "- (2)获取到的value的值为:" + value.get());
        }).start();
    }

}
