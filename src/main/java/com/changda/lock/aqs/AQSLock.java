package com.changda.lock.aqs;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// 自己实现(独享锁) - 常用的
public class AQSLock implements Lock {
    // 抽象工具类AQS
    MyAQS aqs = new MyAQS(){

        @Override
        public boolean tryAcquire() {
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
        public boolean tryRelease() {
            // 释放锁
            int i = state.decrementAndGet();
            if (i == 0) {
                return owner.compareAndSet(Thread.currentThread(), null);
            } else if (i < 0) {
                throw new IllegalMonitorStateException();
            }
            return false;
        }
    };

    @Override
    public boolean tryLock() {
        return aqs.tryAcquire();
    }

    @Override
    public void lock() {
        aqs.acquire();
    }

    @Override
    public void unlock() {
        aqs.release();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
