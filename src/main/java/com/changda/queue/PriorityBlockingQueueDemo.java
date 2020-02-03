package com.changda.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @program: JucAndJvm
 * @classname: PriorityBlockingQueueDemo
 * @description:
 * @author: 南街
 * @create: 2020-01-01 17:49
 **/
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Object> blockingQueue = new PriorityBlockingQueue<>(2);
        // put方法不阻塞，因为无界，不限制大小，自动扩容
        // offer方法不限制
        // 默认情况下按自然排序升序排列，可以传递Comparator
        blockingQueue.put(9);
        blockingQueue.put(1);
        blockingQueue.put(8);
        System.out.println("size = " + blockingQueue.size());
        System.out.println("take = " + blockingQueue.take());
        System.out.println("print = " + blockingQueue);
    }
}
