package edu.changda.classloader;

/**
 * @program: JucAndJvm
 * @classname: Lazy
 * @description:
 * @author: 南街
 * @create: 2020-01-16 13:06
 **/
public class Lazy {

    private static boolean initialized = false;

    // Lazy static 模块执行时(类不完全初始化), Runnable 匿名内置类随之初始化
    // 如果 Runnable 匿名内置类的初始化(组成字节码)
    static {
        // static 模块是通过main线程运行的
        println("static 模块执行");
        // 子线程
        Thread t = new Thread(
                // Case 1： 匿名内部类的类初始化依赖于外部初始化 （如果没有调用Lazy的属性或方法则可以正常运行）
                // 不能依赖于外层类的初始化
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println();
//                    }
//                });
        // Case 2：lambda 表达式，invokedynamic 指令实现（不能运行）
        // invokedynamic 指令作为Lazy字节码的一部分，需要等待Lazy类加载的完成
//                        () -> { initialized = true; });

        // Case 3：方法引用: invokedynamic 指令实现（能够运行）
        // System.out::println 方法属于PrintStream类，它被Bootstrap ClassLoader加载
        // 早于Lazy.class (ApplicationClassLoader,SystemClassLoader)
        // 加载的外部类和当前类在类初始化阶段不能有相互依赖，否则容易相互等待
        System.out::println);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(initialized);
    }

    private static void println(Object object) {
        System.out.printf("线程 [%s] - %s", Thread.currentThread().getName(), object);
    }
}
