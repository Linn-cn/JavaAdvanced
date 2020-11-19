package edu.changda.jmm;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname autoIncrement
 * @description
 * @create 2020-04-03 13:50
 **/
public class AutoIncrement {

    public volatile int inc = 0;

    public void autoIncrement() {
        inc++;
    }

    public static void main(String[] args) {
       final AutoIncrement autoIncrement = new AutoIncrement();
       for (int i = 0; i < 10; i++) {
           new Thread(() -> {
               for (int j = 0; j < 10000; j++)
                   autoIncrement.autoIncrement();
           }, "线程" + i).start();
       }

       //保证前面的线程都执行完，之所以大于2是因为idea中还有一个Monitor Ctrl-Break 线程
       while (Thread.activeCount() > 2) {
           Thread.yield();
       }
       Thread.currentThread().getThreadGroup().list();
       System.out.println(autoIncrement.inc);
    }
}
