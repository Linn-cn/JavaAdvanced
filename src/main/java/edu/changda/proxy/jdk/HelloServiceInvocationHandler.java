package edu.changda.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Linn-cn
 * @create 2020/09/12
 */
public class HelloServiceInvocationHandler implements InvocationHandler {

    private Object target;

    public HelloServiceInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是jdk动态代理 --- begin");
        Object result = method.invoke(target, args);
        System.out.println("我是jdk动态代理 --- end");
        return result;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
          target.getClass().getInterfaces(),
          this);
    }

}
