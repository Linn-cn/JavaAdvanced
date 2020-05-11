package com.changda.observe;

import java.util.concurrent.TimeUnit;

/**
 * @program: JucAndJvm
 * @classname: ProducerAndConsumer
 * @description:
 * @author: 南街
 * @create: 2020-01-15 16:21
 **/
public class ProducerAndConsumer {
    public static void main(String[] args) {
        Storage<Phone> storage = new Storage<Phone>();

        new Thread(() -> {
            for(int i = 0;i<20;i++){
                storage.produce(new Phone(i));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for(int i = 0;i<20;i++){
                storage.consume();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
