package com.changda.queue;

import java.util.concurrent.*;

/**
 * @program: JucAndJvm
 * @classname: BlockingQueueDemo
 * @description:
 * 两个数据结构：栈、队列
 * 栈 后进先出
 * 队列 先进先出
 * 阻塞队列 必须要阻塞、不得不阻塞
 * @author: 南街
 * @create: 2019-12-08 15:02
 **/
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        BlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<>(3);
        BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(3);
        BlockingQueue<String> stringBlockingQueue = new SynchronousQueue<>();
        queueToString(arrayBlockingQueue);
        queueToString(priorityBlockingQueue);
        queueToString(linkedBlockingQueue);
        queueToString(stringBlockingQueue);
/*        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

//        System.out.println(blockingQueue.add("x"));

        System.out.println(blockingQueue.remove());*/
/*        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/

//        blockingQueue.put("a");
//        blockingQueue.put("a");
//        blockingQueue.put("a");
//        blockingQueue.put("a");
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
    }


    public static void queueToString(BlockingQueue queue) throws InterruptedException {
        System.out.println("offer(2) = " + queue.offer(3));
        System.out.println("offer(1) = " + queue.offer(1));
        System.out.println("offer(3) = " + queue.offer(2));
        System.out.println("take = " + queue.take());
        System.out.println("size = " + queue.size());
    }
}
