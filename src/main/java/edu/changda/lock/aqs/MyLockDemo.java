package edu.changda.lock.aqs;

import java.util.concurrent.locks.Lock;

public class MyLockDemo {
    volatile int i = 0;

    // Lock lock = new AQSLock();
    Lock lock = new MyLock();

    public void add() { // 参考我的源码注释。
        lock.lock();
        try {
            System.out.println("....");
            // lock.lock();
            i++;
            // lock.unlock();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyLockDemo ld = new MyLockDemo();

        for (int i = 0; i < 4; i++) {
            new Thread(ld::add).start();
        }
        Thread.sleep(2000L);
        System.out.println(ld.i);
    }
}
