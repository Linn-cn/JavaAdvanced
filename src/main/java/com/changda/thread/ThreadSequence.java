package com.changda.thread;

import java.util.concurrent.TimeUnit;

/**
 * @program: JucAndJvm
 * @classname: ThreadSequence
 * @description:  保证线程的有序执行
 * @author: 南街
 * @create: 2019-12-22 12:07
 **/
public class ThreadSequence {
    public static void main(String[] args) throws InterruptedException {
//        oneDemo();
//        twoDemo();
        threeDemo();
    }

    public static void oneDemo() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println("t1执行");
        }, "t1");
        Thread thread2 = new Thread(() -> {
            System.out.println("t2执行");
        }, "t2");
        Thread thread3 = new Thread(() -> {
            System.out.println("t3执行");
        }, "t3");
        thread1.start();
        // 总结，Thread.join其实底层是通过wait/notifyAll来实现线程的通信达到线程阻塞的目的；
        // 当线程执行结束以后，会触发两个事情，第一个是设置native线程对象为null；
        // 第二个是通过notifyAll方法，让等待在这个对象锁上的wait方法被唤醒。
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }

    public static void twoDemo() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1执行");
        }, "t1");
        Thread thread2 = new Thread(() -> {
            System.out.println("t2执行");
        }, "t2");
        Thread thread3 = new Thread(() -> {
            System.out.println("t3执行");
        }, "t3");

        thread1.start();
        while (thread1.isAlive()) {

        }
        thread2.start();
        while (thread2.isAlive()) {

        }
        thread3.start();
        while (thread3.isAlive()) {

        }
    }

    public static void threeDemo() {
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行");
        }, "t1");
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行");
        }, "t2");
        Thread thread3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行");
        }, "t3");

        threadStartAndWait(thread1);
        threadStartAndWait(thread2);
        threadStartAndWait(thread3);
    }

    public static void threadStartAndWait(Thread thread) {
        System.out.println(Thread.currentThread().getName());
        if (Thread.State.NEW.equals(thread.getState())) {
            thread.start();
        }

        // Java Thread 对象和实际 JVM 执行的OS Thread 不是相同对象
        // JVM Thread 回调 Java Thread.run() 方法
        // 同时 Thread 提供一些 native 方法获取 JVM Thread 状态
        // 当 JVM Thread 执行完之后，自动就notify()了，锁的如果是线程对象而不是普通对象，那么锁对象执行完毕后，会调用自身对象上的notify();
        while (thread.isAlive()) {  // thread 特殊的object
            // 当线程Thread isAlive() == false 时,thread.wait() 操作会被自动释放
            synchronized (thread) {
                try {
                    // 阻塞的是这个对象所在的线程（通常是主线程)
                    thread.wait(); // 到底是谁在通知Thread -> thread.notify();
                    System.out.println("=========");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
