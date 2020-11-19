package edu.changda.pool.executor;


import java.util.concurrent.*;

class MyTask extends RecursiveTask<Integer>{

    private static final Integer ADJUST_VALUE = 10;

    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    /**
     * 伪代码如下：
     * 如果(当前工作部分足够小)
     *      直接做这项工作
     * 其他
     *      把当前工作分成两部分
     *      调用这个两部分并等待结果
     * @return
     */
    @Override
    protected Integer compute() {
        if ((end - begin) <= ADJUST_VALUE){
            System.out.println(Thread.currentThread().getName() + "正在处理!!!");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        }else{
            int middle = (end + begin) / 2;
            MyTask task01 = new MyTask(begin,middle);
            MyTask task02 = new MyTask(middle + 1,end);
            task01.fork();
            task02.fork();
            result = task01.join() + task02.join();
        }
        return result;
    }
}

/**
 * ForkJoinPool专门用来解决那种可拆分的任务，使多个任务可以并行然后合并结果集，不需要串行化执行
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0,100);
        ForkJoinPool threadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors(),
                ForkJoinPool.defaultForkJoinWorkerThreadFactory,
                null, true);
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);
        System.out.println(forkJoinTask.get());
        threadPool.shutdown();
    }
}
