package com.changda.lock.aqs;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname MyLock
 * @description
 * @create 2020-05-13 20:24
 **/
public class MyLock implements Lock {
    // 如何判断一个资源的拥有者
    public volatile AtomicReference<Thread> owner = new AtomicReference<>();
    // 保存 正在等待的线程
    public volatile LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();
    // 同步资源状态
    public volatile AtomicInteger state = new AtomicInteger(0);

    @Override
    public void lock() {
        final Thread thread = Thread.currentThread();
        boolean addQueue = true;
        // 尝试修改锁持有线程
        while (!tryLock()) {
            // 获取锁失败，入队
            if (addQueue) {
                waiters.offer(thread);
                addQueue = false;
            } else {
                System.out.println(thread.getName() + "睡眠");
                // 睡眠
                LockSupport.park();
            }
        }
        if(!addQueue){
            waiters.remove(Thread.currentThread()); // 把线程移除
        }
        System.out.println(thread.getName() + "获得锁");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        final Thread thread = Thread.currentThread();
        // 判断锁状态是不是0
        if (state.get() == 0) {
            // 尝试修改锁持有线程
            if (owner.compareAndSet(null, thread)){
                state.incrementAndGet();
                return true;
            }
        } else if (thread.equals(owner.get())) {
            // 实现可重入
            state.incrementAndGet();
            return true;
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        // 释放锁
        int i = state.get() - 1;
        if (i == 0) {
            if (owner.compareAndSet(Thread.currentThread(), null)) {
                state.decrementAndGet();
                System.out.println(Thread.currentThread().getName() + "释放锁");
                // 通知等待者
                for (Thread next : waiters) {
                    LockSupport.unpark(next); // 唤醒
                }
            }
        } else if (i < 0) {
            throw new IllegalMonitorStateException();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("线程1运行");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            System.out.println("线程2运行");
        }).start();
    }
}
