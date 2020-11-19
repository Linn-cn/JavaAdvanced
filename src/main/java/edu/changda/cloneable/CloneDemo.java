package edu.changda.cloneable;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname CloneDemo
 * @description
 * @create 2020-06-07 12:58
 **/
public class CloneDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        User user = new User().setUsername("南街").setAge("21");
        User user1 = (User) user.clone();
        System.out.println(user == user1);
        System.out.println(user.getUsername() == user1.getUsername());
    }
}
