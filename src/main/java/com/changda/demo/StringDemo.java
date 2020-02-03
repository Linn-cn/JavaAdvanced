package com.changda.demo;

/**
 * @program: JucAndJvm
 * @classname: StringDemo
 * @description:
 * @author: 南街
 * @create: 2019-12-23 10:13
 **/
public class StringDemo {
    public static void main(String[] args) {
        final String pig = "length: 10";
        final String dog = "length: " + pig.length();
        System.out.println("Animals are equals:" + (pig == dog));
    }
}
