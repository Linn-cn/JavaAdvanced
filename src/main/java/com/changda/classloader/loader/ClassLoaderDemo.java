package com.changda.classloader.loader;


/**
 * @author 南街
 * @program JavaAdvanced
 * @classname BootstrapClassLoader
 * @description
 * @create 2020-04-21 14:48
 **/
public class ClassLoaderDemo {
    public static void main(String[] args) {
        // 根类加载器[启动类加载器]打印出来是null
        ClassLoader classLoader1 = Object.class.getClassLoader();
        System.out.println("classLoader1 = " + classLoader1);
        System.out.println("-----------------------------");
        // 应用类加载器[Application、System系统类加载器] 一般用于加载用户自定义的类加载器
        ClassLoader classLoader2 = ClassLoaderDemo.class.getClassLoader();
        System.out.println("classLoader2 = " + classLoader2);
    }
}
