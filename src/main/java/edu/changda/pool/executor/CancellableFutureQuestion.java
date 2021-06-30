package edu.changda.pool.executor;

import java.util.concurrent.*;

/**
 * @program: JucAndJvm
 * @classname: CancellableFutureQuestion
 * @description:
 * @author: 南街
 * @create: 2020-01-15 19:33
 **/
public class CancellableFutureQuestion {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 3 秒内执行完成，才算正常
        Future<?> future = executorService.submit(() -> {
            action(3);
        });

        try {
            future.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // Thread 恢复中断状态C
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException();
        } catch (TimeoutException e) {
            // 执行超时，适当地关闭
            Thread.currentThread().interrupt();
            future.cancel(true);
        }
        executorService.shutdown();
    }

    private static void action(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
            // 判断并且清除中断状态
            if (Thread.interrupted()) {
                return;
            }
            action();
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }

    private static void action() {
        System.out.printf("线程[%s] 正在执行....\n", Thread.currentThread().getName());
    }
}
