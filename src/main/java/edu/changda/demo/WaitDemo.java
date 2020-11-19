package edu.changda.demo;

import java.util.concurrent.TimeUnit;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname WaitDemo
 * @description
 * @create 2020-06-13 22:32
 **/
public class WaitDemo {

    public static class Student {

        public void print() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Student student = new Student();
        Thread thread = new Thread(student::print);
        thread.start();
        synchronized (thread){
            try {
                // 会被自动唤醒
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("main 结束");
    }
}
