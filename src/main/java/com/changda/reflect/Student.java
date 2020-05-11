package com.changda.reflect;

import java.time.Period;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname Student
 * @description
 * @create 2020-03-17 18:10
 **/
public class Student extends Person {

    public String phoneNumber;

    public String school;

    public Student(String phoneNumber, String school) {
        this.phoneNumber = phoneNumber;
        this.school = school;
    }
}
