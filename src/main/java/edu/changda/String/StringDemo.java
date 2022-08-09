package edu.changda.String;

import org.openjdk.jol.info.ClassLayout;

/**
 * @program: JucAndJvm
 * @classname: StringDemo
 * @description: 一个空字符串对象占多大空间
 * @author: 南街
 * @create: 2019-12-23 10:13
 **/
public class StringDemo {
    public static void main(String[] args) {
        String a = new String();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        System.out.println(ClassLayout.parseInstance(new NullObject()).toPrintable());
    }
}
