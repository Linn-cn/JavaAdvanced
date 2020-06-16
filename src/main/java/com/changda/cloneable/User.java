package com.changda.cloneable;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname User
 * @description
 * @create 2020-06-07 12:56
 **/
public class User implements Cloneable {
    private String username;
    private String age;

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getAge() {
        return age;
    }

    public User setAge(String age) {
        this.age = age;
        return this;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        User clone = (User) super.clone();
        return clone.setUsername(new String("南街"));
    }
}
