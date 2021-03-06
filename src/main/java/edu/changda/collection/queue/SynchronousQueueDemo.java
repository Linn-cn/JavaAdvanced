package edu.changda.collection.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * @program: JucAndJvm
 * @classname: SynchronousQueueDemo
 * @description:
 * @author: 南街
 * @create: 2020-01-01 17:53
 **/
public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Object> queue = new SynchronousQueue<>();
        // SynchronousQueue是无空间，offer永远返回false;
        // SynchronousQueue take方法会被阻塞，必须被其他线程显示地调用 put
        new Thread(() -> {
            try {
                queue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // System.out.println("offer(1) = " + queue.put(1););
        // System.out.println("offer(2) = " + queue.offer(2));
        // System.out.println("offer(3) = " + queue.offer(3));
        new Thread(() -> {
            try {
                System.out.println("take = " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // System.out.println("take = " + queue.take());
        System.out.println("size = " + queue.size());
    }
}
