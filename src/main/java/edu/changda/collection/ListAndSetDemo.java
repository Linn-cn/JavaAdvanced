package edu.changda.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: Java9New
 * @classname: ListAndSetDemo
 * @description:
 * @author: 朱林
 * @create: 2019-10-13 15:50
 **/
public class ListAndSetDemo {
    public static void main(String[] args) {
        // HashSet 并不能保证顺序
        Set<String> values = new HashSet<String>();
        // 有些常见可能让你误导
        // 字符场景
        values.add("a");
        values.add("b");
        values.add("c");
        values.forEach(System.out::println);
        values.clear();
        // 数字场景
        values.add("1");
        values.add("2");
        values.add("3");
        values.forEach(System.out::println);
        // 以上栗子是 ASCII 码
        // HashSet 或者 HashMap 采用对象 HashCode
        // String hashCode 由 char[] 数组构建
        // 在 Java 中 char(2字节) 相当于 int(4字节)
        // 汉字 用 2个char 表达，相当于一个int
    }
}
