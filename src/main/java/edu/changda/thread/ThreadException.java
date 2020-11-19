package edu.changda.thread;

/**
 * @program: JucAndJvm
 * @classname: ThreadException
 * @description: 线程异常处理
 * @author: 南街
 * @create: 2019-12-22 12:01
 **/
public class ThreadException {
    // 设置全局的默认未捕获异常处理程序
    static {
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(t.getName() + ":" + e.getMessage());
        });
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            throw new RuntimeException("错误信息");
        },"t1");
        // thread.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
        //     System.out.println(t.getName() + ":" + e.getMessage());
        // });
        thread.start();
        thread.join();
        System.out.println("测试异常捕获结束");
    }
}
