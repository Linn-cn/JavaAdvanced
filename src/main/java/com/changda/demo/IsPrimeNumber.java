package com.changda.demo;

import java.util.HashSet;

/**
 * @author 南街
 * @program JvmAndJuc
 * @classname Demo
 * @description
 * @create 2020-02-19 12:10
 **/
public class IsPrimeNumber {
    private static HashSet set = new HashSet(){{
        add(2);
        add(3);
        add(5);
        add(7);
    }};
    public static void main(String[] args) {
        isPrimeNumber();
    }

    private static void isPrimeNumber() {
        for (int i = 2; i < 1000; i++) {
            if (isPrime(i)) {
                boolean flag = true;
                int temp = i;
                while (temp > 0) {
                    int right = temp % 10;
                    if (!set.contains(right)){
                        flag = false;
                        break;
                    }
                    temp = temp / 10;
                }
                if (flag) System.out.println(i + "");
            }
        }
    }

    /**
     * 判断是不是素数
     * @param number
     * @return boolean
     * @date 2020/3/29 13:52
     */
    public static boolean isPrime(int number) {
        if (number == 1) return false;
        for (int j = 2; j <= Math.sqrt(number); j++) {
            if (number % j == 0) {
                return false;
            }
        }
        return true;
    }
}
