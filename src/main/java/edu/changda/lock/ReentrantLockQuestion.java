package edu.changda.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JucAndJvm
 * @classname: ReentrantLockQuestion
 * @description: Reentrant可重入案例
 * @author: 南街
 * @create: 2020-01-11 11:05
 **/
public class ReentrantLockQuestion {

    /**
     * T1, T2, T3
     *
     * T1(lock), T2(park), T3(park)
     * Waited Queue -> Head-> T2 next -> T3
     * T1(unlock) -> unPark
     * 全部释放掉，自由竞争
     * Waited Queue -> Head-> T2 next -> T3
     * T2(free), T3(free)
     *
     * -> T2(lock),T3(park)
     * Waited Queue -> Head -> T3
     * T2(unlock) -> unPark all
     * T3(free)
     */

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        // main -> action1 -> action2 -> action3
        synchronizedAction(ReentrantLockQuestion::action1);
//        lockVsLockInterruptibly();
    }

    private static void lockVsLockInterruptibly(){
        try {
            lock.lockInterruptibly();
            action1();
        } catch (InterruptedException e){
            // 显示的回复中断状态
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    private static void action1(){
        synchronizedAction(ReentrantLockQuestion::action2);
    }

    private static void action2(){
        synchronizedAction(ReentrantLockQuestion::action3);
    }

    private static void action3(){
        System.out.println("Hello world");
    }

    private static void synchronizedAction(Runnable runnable){
        /**
         *
         */
        lock.lock();
        try {
            runnable.run();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
