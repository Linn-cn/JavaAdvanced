package com.changda.proxy;

import java.util.StringJoiner;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname Student
 * @description
 * @create 2020-02-20 14:39
 **/
public class Student {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
