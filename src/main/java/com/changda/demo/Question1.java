package com.changda.demo;

/**
 * @author Linn-cn
 * @classname Question
 * @description
 * @create 2020-07-28 16:05
 **/
public class Question1 {
    public static void main(String[] args) {
        boolean flag = false;

        try {
            if (flag) {
                while (true) {

                }
            } else {
                System.exit(1);
            }
        } finally {
            System.out.println("In Finally");
        }
    }
}
