package edu.changda.reflect;

import java.lang.reflect.Field;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname relectDemo
 * @description 测试通过子类对象能否拿到父类的私有属性
 * @create 2020-03-17 18:11
 **/
public class relectDemo {
    public static void main(String[] args) throws IllegalAccessException {
        Student student = new Student("x112131", "测试学校");
        Field[] fields = student.getClass().getSuperclass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("name")){
                field.setAccessible(true);
                System.out.println(field.get(student));
            }
        }
    }
}
