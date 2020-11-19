package edu.changda.demo;

import java.lang.reflect.Field;

/**
 * @program: JucAndJvm
 * @classname: StringDemo
 * @description:
 * @author: 南街
 * @create: 2019-12-23 10:13
 **/
public class StringDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // case 1
        // String a = "abc";
        // String b = new String("abc");
        // b = b.intern();
        // System.out.println(a == b);

        String s = "abc";
        Field field = String.class.getDeclaredField("value");
        // private -> 可访问的
        field.setAccessible(true);
        char[] values = (char[]) field.get(s);
        values[0] = 'd';

        System.out.println(s);
    }
}
