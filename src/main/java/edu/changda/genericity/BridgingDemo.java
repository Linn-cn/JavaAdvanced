package edu.changda.genericity;

import java.lang.reflect.Method;

/**
 * @program: JucAndJvm
 * @classname: bridgingDemo
 * @description: 泛型擦除之桥接方法
 * @author: 南街
 * @create: 2020-01-13 10:01
 **/
public class BridgingDemo {
    public static void main(String[] args) {
        InfoImpl info = new InfoImpl();
        Class<? extends InfoImpl> aClass = info.getClass();
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + ":" + method.getReturnType().getSimpleName());
        }
    }
}
