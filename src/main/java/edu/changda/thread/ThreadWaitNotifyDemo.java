package edu.changda.thread;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirConditioner{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // 1. 判断
            while (number != 0) {
                condition.await();
            }
            // 2. 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 3. 通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

/*    public void increment() throws InterruptedException {
        // 1. 判断
        while (number != 0) {
            this.wait();
        }
        // 2. 干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        // 3. 通知
        this.notifyAll();
    }*/

    public synchronized void decrement() throws InterruptedException {
        lock.lock();
        try {
            // 1. 判断
            while (number == 0) {
                condition.await();
            }
            // 2. 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 3. 通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

/*    public synchronized void decrement() throws InterruptedException {
        while (number == 0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }*/
}

/**
 * @program: JucAndJvm
 * @classname: ThreadWaitNotifyDemo
 * @description: 两个线程，操作一个初始值为0的变量，实现一个线程+1，一个线程-1，交替10轮，变量为0
 * 1. 高聚低合前提下，线程操作资源类
 * 2. 判断/干活/通知
 * 3. 多线程交互中，必须有防止多线程的虚假唤醒，也即（判断只用while，不能用if）
 * @author: 朱林
 * @create: 2019-11-19 20:31
 **/
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
/*        HashMap<String,Integer> map = new HashMap<>();
        map.put("测试",1);
        map.put("测试1",2);
        map.forEach((String key, Integer value) -> {
            System.out.println(key + ":" + value);
        });*/
        AirConditioner airConditioner = new AirConditioner();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}
