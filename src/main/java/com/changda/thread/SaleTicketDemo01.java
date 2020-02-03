package com.changda.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket   //资源类 = 实例变量 + 实例方法
{
    private int number = 30;

    private Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0)
                System.out.println(Thread.currentThread().getName() + "\t卖出第:" + (number--) + "还剩下:" + number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * @program: JucAndJvm
 * @classname: SaleTicketDemo01
 * @description: 复制小括号，写死箭头，落地大括号
 * @author: 南街
 * @create: 2019-11-19 15:10
 **/
public class SaleTicketDemo01 {
    public static void main(String[] args) {    //主线程,一切程序的入口
        Ticket ticket = new Ticket();
        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.sale(); }, "A").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.sale(); }, "B").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.sale(); }, "C").start();
    }

}
