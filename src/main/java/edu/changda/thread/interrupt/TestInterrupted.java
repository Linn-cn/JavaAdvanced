package edu.changda.thread.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JucAndJvm
 * @classname: Test
 * @description: t1先执行，但是sleep不释放锁资源，在这期间t2等候两秒钟还没拿到锁就中断
 * @author: 南街
 * @create: 2020-01-26 15:32
 **/
public class TestInterrupted {
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            testsync();
        },"t1");
        Thread t2 = new Thread(() -> {
            testsync();
        },"t2");
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t2.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("main");
        /**
         * 如果t2两秒钟还拿不到就中断
         */
        t2.interrupt();
    }

    public static void testsync(){
        try {
            /**
             * lockInterruptibly 和 lock的区别，前者会直接抛出异常可以响应中断，后者则不可以
             */
            lock.lock();
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
