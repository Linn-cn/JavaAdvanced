package edu.changda.thread.exception;

/**
 * @program: JucAndJvm
 * @classname: ThreadException
 * @description: 线程异常处理
 * @author: 南街
 * @create: 2019-12-22 12:01
 **/
public class ThreadException {
    // 为所有线程设置默认的未捕捉异常处理器
    static {
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println("全局异常处理：" + t.getName() + ":" + e.getMessage());
        });
    }
    public static void main(String[] args) {
        try {
            Thread thread1 = new Thread(() -> {
                throw new RuntimeException("t1错误信息");
            },"t1");
            // 为某个线程单独设置异常处理器
            thread1.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
                System.out.println("t1的异常处理器：" + t.getName() + ":" + e.getMessage());
            });
            thread1.start();
            thread1.join();

            Thread thread2 = new Thread(() -> {
                throw new RuntimeException("错误信息");
            },"t2");
            thread2.start();
            thread2.join();
        } catch (Exception e) {
            System.out.println("测试主线程能不能捕获到异常！");
        }
    }
}
