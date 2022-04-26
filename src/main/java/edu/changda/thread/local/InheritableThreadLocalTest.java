package edu.changda.thread.local;

import java.text.MessageFormat;

/**
 * ThreadLocal在父子线程传递
 * @author Zhu Lin
 * @date 2021/7/22
 */
public class InheritableThreadLocalTest {

  private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

  public static void main(String[] args) {
    threadLocal.set("我是主线程设置的值");
    new Thread(() -> {
      System.out.println(String.format("子线程读到的值：%s", threadLocal.get()));
    }).start();
    System.out.println(String.format("父线程读到的值：%s", threadLocal.get()));
  }
}
