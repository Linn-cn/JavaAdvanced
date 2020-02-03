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
        Class<?> simple = Class.forName("com.changda.classloader.Simple");
        Method simpleSout = simple.getMethod("simpleSout", Simple.class);
        Class<Simple> aClass = Simple.class;
        Simple simple1 = (Simple) simple.newInstance();
        simple1.simpleSout();
        simple1.simpleSout();
        Class<?> aClass1 = ClassLoader.getSystemClassLoader().loadClass("com.changda.classloader.Simple");
        Simple simple11 = (Simple) aClass.newInstance();
        simple1.simpleSout();
    }
}
