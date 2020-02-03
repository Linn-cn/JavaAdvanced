package com.changda.lock;


import org.openjdk.jol.info.ClassLayout;

/**
 * 64位的jvm规定，对象的大小一定要是8的整数倍
 * 对象的组成：
 * 1.java对象的实例数据 --- 不固定
 * 2.对象头 --- 固定
 * 3.数据对齐(对齐填充)
 */
class L{
    boolean flag = false; // 1 byte
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
    public static L l = new L();
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(l).toPrintable());
//        lockTest();
    }

    public static void lockTest(){
        /**
         * Java当中有哪些锁？
         * 锁什么？锁代码块还是对象？
         */
        synchronized (l){
            System.out.println("xxxx");
        }
    }

}
