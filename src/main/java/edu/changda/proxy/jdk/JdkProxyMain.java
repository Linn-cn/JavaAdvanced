package edu.changda.proxy.jdk;

import edu.changda.proxy.HelloService;
import edu.changda.proxy.HelloServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @author Linn-cn
 * @create 2020/09/12
 */
public class JdkProxyMain {
    public static void main(String[] args) {
        HelloService helloService = (HelloService) Proxy.newProxyInstance(JdkProxyMain.class.getClassLoader(),
                new Class[]{HelloService.class},
                new HelloServiceInvocationHandler(new HelloServiceImpl())
        );
        helloService.sayHello("Linn");
    }
}
