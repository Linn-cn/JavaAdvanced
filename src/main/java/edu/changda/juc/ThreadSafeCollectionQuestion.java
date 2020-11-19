package edu.changda.juc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: JucAndJvm
 * @classname: ThreadSafeCollectionQuestion
 * @description:
 * @author: 南街
 * @create: 2020-01-01 13:50
 **/
public class ThreadSafeCollectionQuestion {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
//        Set<Integer> set = Set.of(1, 2, 3, 4, 5);
//        Map<Integer, String> map = Map.of(1, "A");

        // 以上实现都是不变对象，不过第一个除外

        // 通过Collections#synchronized* 方法返回

        // Wrapper 设计模式(所有方法都是synchronized加持)
        list = Collections.synchronizedList(list);
//        set = Collections.synchronizedSet(set);
//        map = Collections.synchronizedMap(map);

        list.set(2,9);
        list.forEach(System.out::println);

        list = new CopyOnWriteArrayList<>(list);
//        set = new CopyOnWriteArraySet<>(set);
//        map = new ConcurrentHashMap<>(map);
    }
}
