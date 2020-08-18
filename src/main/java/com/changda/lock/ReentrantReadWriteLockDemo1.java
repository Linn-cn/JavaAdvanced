package com.changda.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description 读写锁降级，不支持升级
 * @author Linn-cn
 * @create 2020/8/18
 */
public class ReentrantReadWriteLockDemo1 {
    final static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args)  {
        /**
         * 这段代码会打印出“获取到了读锁”，但是却不会打印出“成功升级”，因为 ReentrantReadWriteLock 不支持读锁升级到写锁。
         */
        upgrade();
    }

    public static void upgrade(){
        rwl.readLock().lock();
        System.out.println("获取到了读锁");
        rwl.writeLock().lock();
        System.out.println("成功升级");
    }
}
