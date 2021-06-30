package edu.changda.pool.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 演示线程池的submit的提交 Runnable 任务及结果引用的方法
 * @author Zhu Lin
 * @date 2021/6/29
 */
public class ThreadPoolExecutorSubmit {

  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    // 创建Result对象
    Result result = new Result();
    result.setUserName("测试");
    // 提交任务
    Future<Result> future = executor.submit(new Task(result), result);
    Result fr = null;
    try {
      fr = future.get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    System.out.println(fr.getUserName());
    System.out.println(fr.getPassword());
    executor.shutdown();
  }
}

class Result {

  String userName;

  String password;

  public String getUserName() {
    return userName;
  }

  public Result setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public Result setPassword(String password) {
    this.password = password;
    return this;
  }
}

class Task implements Runnable{
  Result r;
  // 通过构造函数传入 result
  Task(Result r){
    this.r = r;
  }

  @Override
  public void run() {
    // 可以操作 result
    System.out.println(r.getUserName());
    r.setPassword("000000");
  }
}