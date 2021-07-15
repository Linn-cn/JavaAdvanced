package edu.changda.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 模拟分级队列调度的模型
 *
 * @author Zhu Lin
 * @date 2021/7/6
 */
public class MultiLevelScheduler {

  /**
   * 紧急任务队列
   */
  private PriorityQueue<Task> urgents;

  /**
   * 多级任务队列
   */
  private ArrayList<LinkedList<Task>> multLevelQueues;

  /**
   * 默认是 3 级队列
   */

  private int level = 3;

  public MultiLevelScheduler() {
    this.init();
  }

  public MultiLevelScheduler(int level) {
    this.level = level;
    this.init();
  }

  private void init() {
    urgents = new PriorityQueue<>();
    multLevelQueues = new ArrayList<>();
    for (int i = 0; i < this.level; i++) {
      multLevelQueues.add(new LinkedList<>());
    }

  }

  @FunctionalInterface
  interface IYieldFunction {
    void yield();
  }

  @FunctionalInterface
  interface ITask {
    void run(IYieldFunction yieldFunction);
  }

  class Task implements Comparable<Task> {

    int level = -1;

    // 需要执行的任务
    ITask task;

    // 优先级
    int priority;

    private boolean yield;

    public Task(ITask task, int priority) {
      this.task = task;
      this.priority = priority;

    }

    @Override
    public int compareTo(Task o) {
      return this.priority - o.priority;
    }

    public int getLevel() {
      return level;
    }

    public void setLevel(int level) {
      this.level = level;
    }

    public void run(IYieldFunction f) {
      this.task.run(f);
    }

    public void setYield(boolean yield) {
      this.yield = yield;
    }

    public boolean isYield() {
      return yield;
    }

  }


  /**
   * 提交的任务优先级小于 100 的会放入紧急队列。每个任务就是一个简单的函数。
   *
   * @param itask    需要执行的函数
   * @param priority 优先级
   * @return void
   * @author Zhu Lin
   * @date 2021/7/6
   */
  public void submit(ITask itask, int priority) {
    Task task = new Task(itask, priority);
    if (priority >= 100) {
      this.multLevelQueues.get(0).add(task);
      task.setLevel(0);
    } else {
      this.urgents.add(task);
    }
  }

  /**
   * 提交的默认任务，优先级等于100，放入多级队列。
   *
   * @param i    需要执行的函数
   * @return void
   * @author Zhu Lin
   * @date 2021/7/6
   */
  public void submit(ITask t) {
    this.submit(t, 100);
  }

  /**
   * 获取需要执行的下一个任务对象
   *
   * @return {@link edu.changda.thread.MultiLevelScheduler.Task}
   * @author Zhu Lin
   * @date 2021/7/9
   */
  private Task next() {
    if (this.urgents.size() > 0) {
      return this.urgents.remove();
    } else {
      for (int i = 0; i < this.level; i++) {
        LinkedList<Task> queue = this.multLevelQueues.get(i);
        if (queue.size() > 0) {
          return queue.remove();
        }
      }
    }
    return null;
  }

  private void runNext() {

    Task nextTask = this.next();

    if (nextTask == null) {
      return;
    }

    if (nextTask.isYield()) {

      return;

    }

    nextTask.run(() -> {

      if (nextTask.level == -1) {

        // high-priority forbid yield

        return;

      }

      nextTask.setYield(true);

      if (nextTask.level < this.level - 1) {

        multLevelQueues.get(nextTask.level + 1).add(nextTask);

        nextTask.setLevel(nextTask.level + 1);

      } else {

        multLevelQueues.get(nextTask.level).add(nextTask);

      }

      this.runNext();

    });

    this.runNext();

  }

  public void start() throws InterruptedException {
    this.runNext();
  }


  public static void main(String[] argv) throws InterruptedException {

    MultiLevelScheduler scheduler = new MultiLevelScheduler();

    // 添加一个紧急任务，优先级为10的任务
    scheduler.submit((IYieldFunction yield) -> {

      System.out.println("Urgent");

    }, 10);

    // 添加一个紧急任务，优先级为0的任务，数字越小，优先级越高
    scheduler.submit((IYieldFunction yield) -> {

      System.out.println("Most Urgent");

    }, 0);

    // 添加一个默认任务，默认优先级为100
    scheduler.submit((IYieldFunction yield) -> {

      System.out.println("A1");

      yield.yield();

      System.out.println("A2");

    });

    scheduler.submit((IYieldFunction yield) -> {

      System.out.println("B");

    });

    scheduler.submit((IYieldFunction f) -> {

      System.out.println("C");

    });

    scheduler.start();


  }

}
