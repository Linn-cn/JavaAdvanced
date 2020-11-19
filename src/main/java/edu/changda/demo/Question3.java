package edu.changda.demo;

import java.util.Collection;
import java.util.Vector;

import static java.util.Arrays.asList;

/**
 * @author Linn-cn
 * @classname Question3
 * @description
 * @create 2020-07-28 16:25
 **/
public class Question3 {
    public static void main(String[] args) {
        // 误区一：Vector不是Collection ，或者不是List
        // 误区二：ConcurrentModificationException 不会在线程安全集合中出现
        Vector<Integer> values = new Vector<>(asList(1, 2, 3));
        remove(values);
        System.out.println(values);
    }

    private static void remove(Collection<Integer> values) {
         for (Integer value : values) {
            values.remove(value);
        }
    }
}
