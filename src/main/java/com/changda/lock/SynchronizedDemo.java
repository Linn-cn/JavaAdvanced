package com.changda.lock;


import org.openjdk.jol.info.ClassLayout;

/**
 * 64位的jvm规定，对象的大小一定要是8的整数倍
 * 对象的组成：
 * 1.java对象的实例数据 --- 固定
 * 2.对象头 --- 固定
 * 3.数据对齐(对齐填充) --- 不一定有
 */
class Obj {
//    boolean flag = false; // 1 byte
    // 7 byte 填充数据
}

/**
 * @program: JucAndJvm
 * @classname: SynchronizedDemo
 * @description:
 * @author: 南街
 * @create: 2019-12-13 10:59
 **/
public class SynchronizedDemo {
    public static Obj obj = new Obj();

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
/*        System.out.println(Integer.toHexString(obj.hashCode()));
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());*/
/*        new Thread(() -> {
            lockTest();
        }).start();
        new Thread(() -> {
            lockTest();
        }).start();*/
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
         * 那么此时的synchronized关键字会被消除
         */
        return new StringBuffer().append(s1).append(s2).append(s3).toString();
    }

}
