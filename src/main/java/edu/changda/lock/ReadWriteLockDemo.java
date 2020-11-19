package edu.changda.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private final Map<String, Object> map = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 写入数据" + key);
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 读取数据");
            TimeUnit.MILLISECONDS.sleep(300);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取成功" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          readWriteLock.readLock().unlock();
        }
    }
}


/**
 * @program: JucAndJvm
 * @classname: ReadWriteLockDemo
 * @description: 多个线程同时读取一个资源类没有任何问题，所以为了满足并发，读取共享总院应该可以同时进行
 * but
 * 如果有一个线程想要去写共享资源，就不应该再有其他线程可以对该资源进行read或write
 * 小总结：
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 * @author: 南街
 * @create: 2019-12-07 17:45
 **/
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache cache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> cache.put(String.valueOf(tempInt), String.valueOf(tempInt)), String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> cache.get(String.valueOf(tempInt)), String.valueOf(i)).start();
        }
    }
}
