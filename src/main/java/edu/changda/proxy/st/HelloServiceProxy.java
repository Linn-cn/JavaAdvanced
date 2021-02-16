package edu.changda.proxy.st;

import edu.changda.proxy.HelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * 静态代理
 * @author Linn-cn
 * @create 2021/02/16
 */
@Slf4j
public class HelloServiceProxy implements HelloService {

    private HelloService service;

    public HelloServiceProxy(HelloService service) {
        this.service = service;
    }

    @Override
    public void sayHello(String name) {
      log.info("静态代理 start");
      service.sayHello(name);
      log.info("静态代理 end");
    }
}
