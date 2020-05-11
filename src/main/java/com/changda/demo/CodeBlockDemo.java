package com.changda.demo;

class CodeZy{
    public CodeZy(){
        System.out.println("CodeZy的构造方法111");
    }
    {
        System.out.println("CodeZy的构造块2222");
    }
    static {
        System.out.println("CodeZy的静态代码块3333");
    }
}

public class CodeBlockDemo {
    public CodeBlockDemo(){
        System.out.println("CodeBlockDemo的构造方法111");
    }
    {
        System.out.println("CodeBlockDemo的构造块2222");
    }
    static {
        System.out.println("CodeBlockDemo的静态代码块3333");
    }
    public static void main(String[] args) {
        System.out.println("=============方法开始执行=========");
        new CodeZy();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        new CodeZy();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        new CodeBlockDemo();
    }
}
