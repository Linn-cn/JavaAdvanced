package com.changda;

import java.util.concurrent.TimeUnit;

class MyNumber {
    volatile int number = 10;

    void setNumber(int number) {
        this.number = number;
    }
}


public class JMMDemo {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();

        new Thread(() -> {
            try {
                System.out.println("线程" + Thread.currentThread().getName() + "开始修改！");
                TimeUnit.SECONDS.sleep(3);
                myNumber.setNumber(12);
                System.out.println("线程" + Thread.currentThread().getName() + "修改了" + myNumber.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        while (myNumber.number == 10) {

        }

        System.out.println("main exit;");
    }
}
