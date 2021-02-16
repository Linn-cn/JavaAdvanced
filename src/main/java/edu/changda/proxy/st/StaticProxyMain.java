package edu.changda.proxy.st;

import edu.changda.proxy.HelloServiceImpl;

/**
 * @author Linn-cn
 * @create 2021/02/16
 */
public class StaticProxyMain {
    public static void main(String[] args) {
        HelloServiceImpl service = new HelloServiceImpl();
        HelloServiceProxy proxy = new HelloServiceProxy(service);
        proxy.sayHello("朱林");
    }
}
