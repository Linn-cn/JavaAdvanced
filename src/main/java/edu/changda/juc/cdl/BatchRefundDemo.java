package edu.changda.juc.cdl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Linn-cn
 * @create 2020/09/23
 */
public class BatchRefundDemo {

    public static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(
            10,
            10,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(20)){
        /**
         * 异常处理
         */
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
        }
    };

    public static void main(String[] args) throws InterruptedException {
        // state 初始化为 30
        CountDownLatch latch = new CountDownLatch(30);
        RefundDemo refundDemo = new RefundDemo();

        // 准备30个商品
        ArrayList<Long> items = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            items.add(Long.valueOf(i + ""));
        }

        // 准备开始批量退款
        ArrayList<Future> futures = new ArrayList<>();
        for (Long item : items) {
            // 使用Callable，因为我们需要等到返回值
            Future<Boolean> future = EXECUTOR_SERVICE.submit(() -> {
                boolean result = refundDemo.refundByItem(item);
                // 每个子线程都会执行CountDown，使state - 1，但只有最后一个才能真的唤醒线程
                latch.countDown();
                return result;
            });
            // 收集批量退款的结果
            futures.add(future);
        }
        System.out.println("30 个商品已经在退款中");
        latch.await();
        System.out.println("30 个商品已经在退款中");
        // 拿到所有结果进行分析
        List<Boolean> result = futures.stream().map(fu -> {
            try {
                // get 的超时时间设置的是 1 毫秒，是为了说明此时所有的子线程都已经执行完成了
                return (Boolean) fu.get(1, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());
        // 打印结果统计
        long success = result.stream().filter(r -> r.equals(true)).count();
        System.out.println(String.format("执行结果成功%d,失败%d",success, result.size() - success));
        EXECUTOR_SERVICE.shutdown();
    }
}
