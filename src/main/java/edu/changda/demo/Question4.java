package edu.changda.demo;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Linn-cn
 * @classname Question4
 * @description
 * @create 2020-07-28 17:46
 **/
public class Question4 {

    /**
     * 考点六：Daemon 线程
     */
    static ExecutorService executorService = Executors.newSingleThreadExecutor((Runnable r) -> {
        Thread thread = new Thread();
        thread.setDaemon(true);
        return thread;
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        action(map);
        // 考点五：合理关闭线程池
        executorService.shutdown();
    }

    private static void action(HashMap<String, Integer> map) throws ExecutionException, InterruptedException {
        Future<Integer> future = executorService.submit(() -> map.get("A"));
        // 考点二：Future#get() 方法是同步阻塞，必须执行完成
        Integer value = future.get();   // Integer
        // 考点三：Boxing 和 Unboxing
        // 考点四：Integer -127 到 128 Cache
        System.out.println(value == 1);
    }
}
