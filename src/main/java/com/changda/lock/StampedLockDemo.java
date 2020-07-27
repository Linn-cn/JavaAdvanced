package com.changda.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author Linn-cn
 * @classname StampedLock
 * @description
 * @create 2020-07-09 21:40
 **/
public class StampedLockDemo {
    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();
        long stamp = stampedLock.tryOptimisticRead();
        Lock readLock = stampedLock.asReadLock();
        readLock.lock();
        try {
            stampedLock.validate(stamp);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            stampedLock.unlock(stamp);
        }
    }
}
