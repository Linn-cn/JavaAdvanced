package com.changda.demo;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname StreamDemo
 * @description
 * @create 2020-05-15 17:04
 **/
public class StreamDemo {
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 20).forEach(System.out::println);
    }
}
