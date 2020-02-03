package com.changda.proxy;

/**
 * @program: JucAndJvm
 * @classname: HelloServiceImpl
 * @description:
 * @author: 南街
 * @create: 2019-12-24 11:32
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello" + name);
    }

    @Override
    public void sayHello1(String name) {
        System.out.println("Hello" + name);
    }
}
