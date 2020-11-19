package edu.changda.lock;


import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 64位的jvm规定，对象的大小一定要是8的整数倍
 * 对象的组成：
 * 1.java对象的实例数据 --- 固定
 * 2.对象头 --- 固定
 * 3.数据对齐(对齐填充) --- 不一定有
 */
class Obj {
// boolean flag = false; // 1 byte
// 7 byte 填充数据

    public static synchronized void print(){
        System.out.println("------------");
    }

    public static synchronized void print1(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("222222222222");
    }

}

/**
 * @program: JucAndJvm
 * @classname: SynchronizedDemo
 * @description:
 * 1. 一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，其它的线程都只能等待，
 * 换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
 *
 * 2. 锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
 *
 * 3. 加个普通方法后发现和同步锁无关
 *
 * 4. 换成两个对象后，不是同一把锁了，情况立刻变化。
 *
 * 5. 都换成静态同步方法后，情况又变化
 *
 * 所有的非静态同步方法用的都是同一把锁——实例对象本身，也就是说如果一个实例对象的非静态同步方法获取锁后，
 * 该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
 * 所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。
 *
 * 所有的静态同步方法用的也是同一把锁——类对象本身，这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
 * 但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，而不管是同一个实例对象的静态同步方法之间，
 * 还是不同的实例对象的静态同步方法之间，只要它们同一个类的实例对象！
 * @author: 南街
 * @create: 2019-12-13 10:59
 **/
public class SynchronizedDemo {
    public static Obj obj = new Obj();

    public static void main(String[] args) throws InterruptedException {
        // System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        // System.out.println(Integer.toHexString(obj.hashCode()));
        new Thread(Obj::print1).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(Obj::print).start();
    }

    public static void lockTest() {
        /**
         * Java当中有哪些锁？
         * 锁什么？锁代码块还是对象？
         */
        for (int i = 0; i < 5; i++) {
            synchronized (obj) {
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            }
        }
    }

    public static String contactString(String s1,String s2,String s3){
        /**
         *     public synchronized StringBuffer append(String str) {
         *         toStringCache = null;
         *         super.append(str);
         *         return this;
         *     }
         * append方法是加锁的，但是虚拟机在运行时会判断是否需要锁消除,
         * 锁消除的主要判定依据来源于逃逸分析的数据支持，如果判断在一段代码中，堆上的所有数据都不会逃逸出去从而被其他线程访问到，
         * 那就可以把它们当做栈上数据对待，认为它们是线程私有的，同步加锁自然就无须进行
         * 例如下面，不管多少个线程进来，new StringBuffer都是不同的对象，就不存在数据竞争，
         * 那么此时的synchronized关键字会被消除 也即"锁消除"
         */
        return new StringBuffer().append(s1).append(s2).append(s3).toString();
    }

}
