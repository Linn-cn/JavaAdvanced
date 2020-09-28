package com.changda.collection.map;

import java.util.HashMap;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname HashMapTest
 * @description
 * @create 2020-02-19 19:59
 **/
public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String,String > hashMap = new HashMap<>(4);
        hashMap.put("周瑜","周瑜");
        hashMap.put("曹操","曹操");
        hashMap.put("刘备","刘备");
        hashMap.put("孙权","孙权");
        hashMap.put("诸葛亮","诸葛亮");
        for (String key : hashMap.keySet()) {
            int index = key.hashCode() % 8;
            System.out.println(key + "的hashcode:" + key.hashCode() + "index:" + index);
        }
    }
}
