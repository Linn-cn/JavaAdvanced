package edu.changda.proxy;

/**
 * @program: JucAndJvm
 * @classname: HelloServiceImpl
 * @description:
 * @author: 南街
 * @create: 2019-12-24 11:32
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("Hello，" + name);
        return name;
    }
}
