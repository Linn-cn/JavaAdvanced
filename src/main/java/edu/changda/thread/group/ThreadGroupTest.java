package edu.changda.thread.group;

/**
 * 线程组
 * @author Linn-cn
 * @create 2020/11/20
 */
public class ThreadGroupTest {
    public static void main(String[] args) {
        // main 线程组
        System.out.println(Thread.currentThread().getThreadGroup());
        // system 线程组
        System.out.println(Thread.currentThread().getThreadGroup().getParent());
    }
}
