package edu.changda.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 对于 ReentrantReadWriteLock 而言。
 *
 * 插队策略
 *
 * 公平策略下：只要队列里有线程已经在排队，就不允许插队。
 *
 * 非公平策略下：
 *
     * 如果允许读锁插队，那么由于读锁可以同时被多个线程持有，所以可能造成源源不断的后面的线程一直插队成功，
     * 导致读锁一直不能完全释放，从而导致写锁一直等待，为了防止“饥饿”，在等待队列的头结点是尝试获取写锁的线程的时候，
     * 不允许读锁插队。
 *
     * 写锁可以随时插队，因为写锁并不容易插队成功，写锁只有在当前没有任何其他线程持有读锁和写锁的时候，才能插队成功，
     * 同时写锁一旦插队失败就会进入等待队列，所以很难造成“饥饿”的情况，允许写锁插队是为了提高效率。
 *
 * 升降级策略：只能从写锁降级为读锁，不能从读锁升级为写锁。
 *
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
