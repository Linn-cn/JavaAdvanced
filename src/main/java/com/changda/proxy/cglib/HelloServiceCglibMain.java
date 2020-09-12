package com.changda.proxy.cglib;

import com.changda.proxy.HelloServiceImpl;
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
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\proxy");
        HelloServiceImpl helloService = new HelloServiceImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloServiceImpl.class);
        enhancer.setCallback(new HelloServiceMethodInterceptor(helloService));
        helloService = (HelloServiceImpl) enhancer.create();
        helloService.sayHello("11");

        // 使用cglib实现bean的复制
//        User user = new User();
//        user.setId(1);
//        user.setName("测试");
//        User user1 = new User();
//        BeanCopier copier = BeanCopier.create(User.class, User.class, false);
//        copier.copy(user,user1,null);
//        System.out.println(user.toString());
//        System.out.println(user1.toString());
//
//        Student student = new Student();
//        BeanCopier copier1 = BeanCopier.create(User.class, Student.class, false);
//        copier1.copy(user,student,null);
//        System.out.println(student.toString());
    }
}
