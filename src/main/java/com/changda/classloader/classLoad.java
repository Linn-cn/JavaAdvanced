package com.changda.classloader;

import java.lang.reflect.Method;

/**
 * @program: JucAndJvm
 * @classname: classLoad
 * @description:
 * @author: 南街
 * @create: 2019-12-22 13:27
 **/
public class classLoad {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        // case 1: forName不仅会加载类并且会初始化类
//        Class<?> simple = Class.forName("com.changda.classloader.Simple");
//        System.out.println("-------------------------------------------");
//        Simple simple1 = (Simple) simple.newInstance();
//        simple1.simpleSout();
//        System.out.println("-------------------------------------------");
        // case 2: 不会初始化类
//        Class<Simple> simple = Simple.class;
//        Simple simple1 = simple.newInstance();
//        simple1.simpleSout();
        // case 3: 不会初始化类
//        Class<?> simple = ClassLoader.getSystemClassLoader().loadClass("com.changda.classloader.Simple");
//        Simple simple1 = (Simple) simple.newInstance();
//        simple1.simpleSout();
    }
}
