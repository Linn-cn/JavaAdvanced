package com.changda.collection.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description linkedHashMap的 "LRU最近最少使用机制"
 * 这种策略也叫做 LRU（Least recently used,最近最少使用），大概的意思就是经常访问的元素会被追加到队尾，这样不经常访问的数据自然就靠近队头，
 * 然后我们可以通过设置删除策略，比如当 Map 元素个数大于多少时，把头节点删除
 * @author Linn-cn
 * @date 2020/9/22
 */
public class LinkedHashMapLRUTest {
    public static void main(String[] args) {
        // 新建 LinkedHashMap
        // accessOrder 开启LRU
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(4,0.75f,true) {
            {
                put(10, 10);
                put(9, 9);
                put(20, 20);
                put(1, 1);
            }

            @Override
            // 覆写了删除策略的方法，我们设定当节点个数大于 3 时，就开始删除头节点
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > 3;
            }
        };
        System.out.println(map);
        System.out.println(map.get(9));
        System.out.println(map.get(20));
    }
}
