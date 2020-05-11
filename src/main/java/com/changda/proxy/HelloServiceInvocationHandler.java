package com.changda.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: JucAndJvm
 * @classname: HelloServiceProxy
 * @description:
 * @author: 南街
 * @create: 2019-12-24 11:33
 **/
public class HelloServiceInvocationHandler implements InvocationHandler {
    private Object target;

    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("############我是jdk动态代理##############");
        System.out.println("我准备说hello");
        Object result = method.invoke(target,args);
        System.out.println("我说过hello了");
        return result;
    }
}
