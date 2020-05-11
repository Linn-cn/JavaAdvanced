package com.changda.jmm;

import java.util.concurrent.TimeUnit;

class MyNumber {
    int number = 10;

    void setNumber(int number) {
        this.number = number;
    }
}

/**
 *  验证volatile的可见性
 *  1.1 变量没添加volatile关键字修饰，没有可见性，（注意synchronized关键字在获取锁和释放锁的时候会刷新主存和自己的内存）
 *  1.2 添加了volatile，可以解决可见性问题
 *  1.3 volatile不保证原子性
 *  什么是原子性？
 *  不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整
 *  要么同时成功，要么同时失败。
 */
public class JMMDemo {
    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();

        new Thread(() -> {
            System.out.println("线程" + Thread.currentThread().getName() + "开始修改！");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNumber.setNumber(12);
            System.out.println("线程" + Thread.currentThread().getName() + "修改了" + myNumber.number);

        }, "A").start();
        while (myNumber.number == 10) {
//            System.out.println("--------");
        }

        System.out.println("main exit;");
    }
}
