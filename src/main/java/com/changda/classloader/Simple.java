package com.changda.classloader;

/**
 * @author Linn-cn
 * @classname Simple
 * @description
 * @create 2020-07-13 23:02
 **/
public class Simple {

    static {
        System.out.println("静态代码块");
    }

    public Simple() {
        System.out.println("调用构造");
    }
}
