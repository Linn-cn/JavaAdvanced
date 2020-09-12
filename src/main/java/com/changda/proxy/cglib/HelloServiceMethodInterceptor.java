package com.changda.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: JucAndJvm
 * @classname: HelloServiceMethodInterceptor
 * @description:
 * @author: 南街
 * @create: 2020-02-01 20:32
 **/
public class HelloServiceMethodInterceptor implements MethodInterceptor {

    private Object target;

    public HelloServiceMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我是cglib动态代理");
        /**
         * 这里需要注意invoke和invokeSuper的区别
         */
        methodProxy.invokeSuper(o,objects);
        return null;
    }
}
