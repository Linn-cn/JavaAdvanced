package com.changda.demo;

/**
 * @program: JucAndJvm
 * @classname: VariableElementDemo
 * @description:
 * @author: 南街
 * @create: 2019-12-06 21:37
 **/
public class VariableElementDemo {
    public static void main(String[] args) {
        int i = 0;
        i= ++i + i++;
        System.out.println(i);

        int j = 0;
        j = j++;
        System.out.println(j);
    }

    public static <T> T[]  getList(T... value){
        return value;
    }
}
