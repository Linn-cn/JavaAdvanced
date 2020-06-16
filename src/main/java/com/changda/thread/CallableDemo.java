package com.changda.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("come in here");
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}

/**
 * @program: JucAndJvm
 * @classname: CallableDemo
 * @description: 可返回值的线程，跟Runnable不一样
 * @author: 南街
 * @create: 2019-11-24 12:53
 **/
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask,"A").start();
        System.out.println(futureTask.get());
        System.out.println(Thread.currentThread().getName() + "计算完成");
    }
}
