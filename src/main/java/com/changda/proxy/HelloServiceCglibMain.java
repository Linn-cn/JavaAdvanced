package com.changda.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @program: JucAndJvm
 * @classname: HelloServiceCglib
 * @description: cglib代理
 * @author: 南街
 * @create: 2020-02-01 20:29
 **/
public class HelloServiceCglibMain {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\proxy");
        HelloServiceImpl helloService = new HelloServiceImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloServiceImpl.class);
        enhancer.setCallback(new HelloServiceMethodInterceptor(helloService));
        helloService = (HelloServiceImpl) enhancer.create();
        helloService.sayHello("11");
    }
}
