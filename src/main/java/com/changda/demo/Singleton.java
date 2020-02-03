package com.changda.demo;

/**
 * @program: JucAndJvm
 * @classname: Singleton
 * @description:
 * @author: 南街
 * @create: 2019-11-20 22:31
 **/
public class Singleton {

    private static final Singleton INSATNCE = new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){ return INSATNCE; }

    private Object readResolve(){
        return INSATNCE;
    }

}
