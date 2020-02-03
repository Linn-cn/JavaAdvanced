package com.changda.classloader;

/**
 * @program: JucAndJvm
 * @classname: User
 * @description:
 * @author: 南街
 * @create: 2019-12-22 13:28
 **/
public class Simple {
    {
        System.out.println("代码块");
    }

    static {
        System.out.println("静态代码块");
    }

    public Simple() {
        System.out.println("构造器");
    }

    public void simpleSout(){
        System.out.println("调用方法");
    }
}
