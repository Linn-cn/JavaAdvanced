package edu.changda.demo;

public class A {

    public void printf(){
        System.out.println("parent printf");
    }

}


class B extends A {

    @Override
    public void printf() {
        System.out.println("测试");
    }

    public void test(){
        System.out.println("测试1");
        super.printf();
    }

    public static void main(String[] args) {

        A ab = new B();
        ab.printf();

    }

}

