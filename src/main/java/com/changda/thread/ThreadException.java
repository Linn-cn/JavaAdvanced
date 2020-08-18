package com.changda.thread;

/**
 * @program: JucAndJvm
 * @classname: ThreadException
 * @description: 线程异常处理
 * @author: 南街
 * @create: 2019-12-22 12:01
 **/
public class ThreadException {
    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(t.getName() + ":" + e.getMessage());
        });
        Thread thread = new Thread(() -> {
            throw new RuntimeException("错误信息");
        },"t1");
        thread.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(t.getName() + ":" + e.getMessage());
        });
        thread.start();
        thread.join();
        System.out.println("错误");
    }
}
