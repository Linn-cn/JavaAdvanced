package edu.changda.thread.future;

import java.util.concurrent.Callable;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname MyFutureTask
 * @description futureTask是怎么实现的？使用了聚合的思想
 * @create 2020-05-26 11:24
 **/
public class MyFutureTask<T> implements Runnable {

    // 业务逻辑在callable里面
    private Callable<T> callable;

    T result = null;

    public MyFutureTask(Callable<T> callable) {
        this.callable = callable;
    }

    // 返回结果Future
    public T get() {
        // 如果没有结束，那么调用get方法的线程，就应该进入等待
        return result;
    }

    @Override
    public void run() {
        try {
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
