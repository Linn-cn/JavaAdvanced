package com.changda.extend;

/**
 * @author Linn-cn
 * @classname multi_extend
 * @description
 * @create 2020-07-27 10:18
 **/
public class MultiExtend {
    public static void main(String[] args) {

    }

    class A {

    }

    class B extends A {

    }

    // 因为Java语言特性导致无法直接通过extends去实现多继承
    // 但1.8以后我们可以通过接口新特性（interface implements）去实现多继承
    class C extends B implements I, I1 {

    }

    interface I {

        default void execute() {

        }

    }

    interface I1 {

        default void action() {
            System.out.println("action");
        }
    }
}
