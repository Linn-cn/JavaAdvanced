package com.changda.demo;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname Person
 * @description
 * @create 2020-04-02 14:22
 **/
public class Person{
    public static void say(){
        String name = "";
        System.out.println("hello");
    }
    public static void main(String[] args) {
        User.test();
        User user = new User();
        user.test();
        Person.say();
        Person man=  new Person();
        man.say();
        Person woman=  new Person();
        woman.say();
    }
}
