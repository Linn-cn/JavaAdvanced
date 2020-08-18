package com.changda.demo;

/**
 * @author Linn-cn
 * @classname Question2
 * @description
 * @create 2020-07-28 16:13
 **/
public class Question2 {
    public static void main(String[] args) {
        try {
            throwNPE();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void throwNPE() {
        // RuntimeException 非 check异常，不需要显示的使用throws
        throw new NullPointerException("Throw");
    }
}
