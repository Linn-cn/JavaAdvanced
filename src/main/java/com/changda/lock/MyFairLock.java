package com.changda.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JucAndJvm
 * @classname: MyFairLock
 * @description: 公平锁
 * @author: 南街
 * @create: 2020-01-10 21:51
 **/
public class MyFairLock extends Thread {
    /**
     * ReentrantLock默认为非公平锁，但是可以通过构造方法创建公平锁
     */
    private ReentrantLock lock = new ReentrantLock(true);
    public void fairLock(){
        try {
            /**
             * tryAcquire分析：↓
             * 公平锁首先会调用tryAcquire去尝试加锁，当然这里的尝试加锁并不是直接加锁，事实上tryAcquire当中
             * 第一步：便是判断锁是不是自由状态，如果是则判断直接是否需要排队
             * （hasQueuedPredecessors方法判断队列是否被初始化（如果没有初始化显然不需要排队），和是否需要排队（队列如果被初始化了，则自己有可能需要排队））；
             * 如果hasQueuedPredecessors返回false，由于取反了故而不需要排队则进行Cas操作去上锁，
             * 如果需要排队则不会进入if分支当中，也不会进else if，会直接返回false表示加锁失败
             * 第二步如果不是自由状态再判断是不是重入，如果不是重入则直接返回false加锁失败，如果是重入则把计数器+1
             */
            lock.lock();
            System.out.println(Thread.currentThread().getName()  + "正在持有锁");
        }finally {
            System.out.println(Thread.currentThread().getName()  + "释放了锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyFairLock myFairLock = new MyFairLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            myFairLock.fairLock();
        };
        Thread[] thread = new Thread[10];
        for(int i = 0;i < 10;i++){
            thread[i] = new Thread(runnable);
        }
        for(int i = 0;i < 10;i++){
            thread[i].start();
        }
    }
}
