package edu.changda.design.consumer;

/**
 * @program: JucAndJvm
 * @classname: Phone
 * @description:
 * @author: 南街
 * @create: 2020-01-15 16:20
 **/
public class Phone {

    private int id;// 手机编号

    public Phone(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "手机编号：" + id;
    }
}
