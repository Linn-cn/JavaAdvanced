package edu.changda.thread;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname ThreadYieldDemo
 * @description
 * @create 2020-03-22 16:39
 **/
public class ThreadYieldDemo {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("线程：" + Thread.currentThread().getName() + " I：" + i);
                if (i == 5) {
                    Thread.yield();
                }
            }
        };
        Thread t1 = new Thread(runnable, "T1");
        Thread t2 = new Thread(runnable, "T2");
        t1.start();
        t2.start();
    }
}
