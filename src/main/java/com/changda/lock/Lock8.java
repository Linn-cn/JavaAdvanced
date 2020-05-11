package com.changda.lock;

import java.util.concurrent.TimeUnit;

class Phone{
    public synchronized void sendEmail(){
        try {
            System.out.println("进来了");
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---sendEmail");
    }

    public synchronized void sendSMS(){
        System.out.println("---sendSMS");
    }

    public void hello(){
        System.out.println("---hello");
    }
}

/**
 * @program: JucAndJvm
 * @classname: Lock8
 * @description:
 * 1. 标准方法，请问先打印邮件还是短信？        邮件、短信
 * 2. 邮件方法暂停4秒钟，请问先打印邮件还是短信？    邮件、短信
 * 3. 新增一个普通方法hello，请问先打印邮件还是hello？     hello、邮件
 * 4. 两部手机，请问先打印邮件还是短信？             短信、邮件
 * 5. 两部个静态同步方法，同一部手机，请问先打印邮件还是短信？  邮件、短信
 * 6. 两部个静态同步方法，2部手机，请问先打印邮件还是短信？     邮件、短信
 * 7. 1个普通同步方法，1个静态同步方法，1部手机，请问先打印邮件还是短信？   短信、邮件
 * 8. 1个普通同步方法，1个静态同步方法，2部手机，请问先打印邮件还是短信？   短信、邮件
 * @author: 南街
 * @create: 2019-11-21 09:30
 **/
public class Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(() ->{
            try {
                phone1.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() ->{
            try {
                phone1.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
