package edu.changda.thread.future;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * CompletableFuture的使用
 *
 * @author Linn-cn
 * @create 2020/08/29
 */
public class CompletableFutureDemo1 {

  public static void main(String[] args) {
    CompletableFutureDemo1 completableFutureDemo1 = new CompletableFutureDemo1();
    System.out.println("completableFutureDemo.getPrices() = " + completableFutureDemo1.getPrices());
  }

  private Set<Integer> getPrices() {
    Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());
    // 使用 runAsync() 运行异步计算
    CompletableFuture<Void> task1 = CompletableFuture.runAsync(new Task(prices));
    CompletableFuture<Void> task2 = CompletableFuture.runAsync(new Task(prices));
    CompletableFuture<Void> task3 = CompletableFuture.runAsync(new Task(prices));

    CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);
    try {
      allTasks.get(3, TimeUnit.SECONDS);
    } catch (InterruptedException | ExecutionException | TimeoutException e) {
      e.printStackTrace();
    }
    return prices;
  }

  private class Task implements Runnable {
    Set<Integer> prices;

    public Task(Set<Integer> prices) {
      this.prices = prices;
    }

    @Override
    public void run() {
      int price = 0;
      try {
        Thread.sleep((long) (Math.random() * 4000));
        price = (int) (Math.random() * 4000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      prices.add(price);
    }
  }
}
