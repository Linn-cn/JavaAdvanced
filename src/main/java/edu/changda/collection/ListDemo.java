package edu.changda.collection;

import java.util.ArrayList;

/**
 * @author Linn-cn
 * @create 2020/09/13
 */
public class ListDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(2);
        list.add(3);
        // 指定下标插入元素
        // 当插入的位置正好处于末尾时，只需要拷贝一次。
        // 当插入的位置处于中间时，此时我们会把原数组一分为二，进行两次拷贝操作。
        list.add(2,4);
        System.out.println(list.toString());
    }
}
