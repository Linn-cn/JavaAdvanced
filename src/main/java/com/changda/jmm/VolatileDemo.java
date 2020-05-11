package com.changda.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname VolatileDemo
 * @description
 * @create 2020-02-27 10:27
 **/
public class VolatileDemo {

    private boolean flag = true;

    public void test() {
        System.out.println("test begin------");
        int i = 0;
        while (flag){
            i++;
//            System.out.println("------");
        }
        System.out.println("test end------" + i);
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo volatileDemo = new VolatileDemo();
        new Thread(volatileDemo::test,"线程1").start();
        TimeUnit.SECONDS.sleep(3);
        volatileDemo.flag = false;
        System.out.println("main end----");
    }
}
