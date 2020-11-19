package edu.changda.genericity;

import java.lang.reflect.Array;

/**
 * @program: JucAndJvm
 * @classname: Fruit
 * @description:
 * @author: 南街
 * @create: 2020-01-13 15:47
 **/
public class Fruit<T> {
    private T[] array;

    public Fruit(Class<T> clz,int length){
        // 通过Array.newInstance创建泛型数组
        array = (T[]) Array.newInstance(clz,length);
    }

    /**
     * 填充数组
     * @param index
     * @param item
     * @return void
     * @date 2020/1/13 15:51
     */
    public void put(int index, T item){
        array[index] = item;
    }

    /**
     * 获取数组元素
     * @param index
     * @return T
     * @date 2020/1/13 15:52
     */
    public T get(int index){
        return array[index];
    }

    public T[] getArray() {
        return array;
    }

    public static void main(String[] args) {
        Fruit<String> fruit = new Fruit<>(String.class, 5);
        fruit.put(0,"abc");
        System.out.println(fruit.get(0));
    }
}
