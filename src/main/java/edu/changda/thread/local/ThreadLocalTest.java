package edu.changda.thread.local;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname ThreadLocalTest
 * @description 线程封闭 -> threadLocal
 * 栈封闭 -> 局部变量 -> 栈中的局部变量表
 * @create 2020-04-25 19:02
 **/
public class ThreadLocalTest {
    /** threadLocal变量，每个线程都有一个副本，互不干扰 */
    public static ThreadLocal<String> value = new ThreadLocal<>();

    /**
     * threadlocal测试
     *
     * @throws Exception
     */
    public void threadLocalTest() throws Exception {

        // threadlocal线程封闭示例
        // 每个线程都有一个threadLocals属性，用来存放ThreadLocalMap引用
        // ThreadLocalMap是一种类似HashMap的散列表结构，初始化大小也为16.但对于哈希冲突的解决是寻找下一个空位(开放定址法)
        // set的时候会判断当前线程的threadLocals是不是null，不是的话就set，否则就创建
        // set的时候，以当前ThreadLocal对象为key，值为value
        value.set("这是主线程设置的123"); // 主线程设置值
        String v = value.get();
        System.out.println("线程1执行之前，主线程取到的值：" + v);

        new Thread(() -> {
            String v1 = value.get();
            System.out.println("线程1取到的值：" + v1);
            // 设置 threadLocal
            value.set("这是线程1设置的456");

            v1 = value.get();
            System.out.println("重新设置之后，线程1取到的值：" + v1);
            System.out.println("线程1执行结束");
        }).start();

        Thread.sleep(5000L); // 等待所有线程执行结束

        v = value.get();
        System.out.println("线程1执行之后，主线程取到的值：" + v);

    }

    public static void main(String[] args) throws Exception {
        new ThreadLocalTest().threadLocalTest();
    }
}
