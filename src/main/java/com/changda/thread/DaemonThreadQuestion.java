package com.changda.thread;

/**
 * @program: JucAndJvm
 * @classname: DaemonThreadQuestion
 * @description:
 * @author: 南街
 * @create: 2020-01-01 13:23
 **/
public class DaemonThreadQuestion {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Hello,World");
        });

        thread.setDaemon(true);
        thread.start();
    }
}
