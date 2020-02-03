package com.changda.proxy;

/**
 * @program: JucAndJvm
 * @classname: HelloServiceMain
 * @description:
 * @author: 南街
 * @create: 2019-12-24 11:36
 **/
public class HelloServiceJdkMain {
    public static void main(String[] args) {
        HelloServiceJdkProxy helloServiceJdkProxy = new HelloServiceJdkProxy();
        HelloService proxy = (HelloService) helloServiceJdkProxy.bind(new HelloServiceImpl());
        proxy.sayHello("张三");
        proxy.sayHello1("李四");
    }
}
