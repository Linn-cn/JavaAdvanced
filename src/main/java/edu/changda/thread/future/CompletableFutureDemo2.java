package edu.changda.thread.future;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * CompletableFuture实现烧水泡茶程序
 *
 * @author Linn-cn
 * @create 2020/08/29
 */
public class CompletableFutureDemo2 {

  public static void main(String[] args) {
    // 任务 1：洗水壶 -> 烧开水
    CompletableFuture<Void> f1 =
      CompletableFuture.runAsync(() -> {
        System.out.println("T1: 洗水壶...");
        sleep(1, TimeUnit.SECONDS);

        System.out.println("T1: 烧开水...");
        sleep(15, TimeUnit.SECONDS);
      });
    // 任务 2：洗茶壶 -> 洗茶杯 -> 拿茶叶
    CompletableFuture<String> f2 =
      CompletableFuture.supplyAsync(() -> {
        System.out.println("T2: 洗茶壶...");
        sleep(1, TimeUnit.SECONDS);

        System.out.println("T2: 洗茶杯...");
        sleep(2, TimeUnit.SECONDS);

        System.out.println("T2: 拿茶叶...");
        sleep(1, TimeUnit.SECONDS);
        return " 龙井 ";
      });
    // 任务 3：任务 1 和任务 2 完成后执行：泡茶
    CompletableFuture<String> f3 =
      f1.thenCombine(f2, (unused, tf) -> {
        System.out.println("T1: 拿到茶叶:" + tf);
        System.out.println("T1: 泡茶...");
        return " 上茶:" + tf;
      });
    // 等待任务 3 执行结果
    System.out.println(f3.join());
  }

  static void sleep(int t, TimeUnit u) {
    try {
      u.sleep(t);
    } catch (InterruptedException e) {
    }
  }
}
