package edu.changda.atomic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@ToString
@AllArgsConstructor
class User {
    String userName;
    int age;
}

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname AtomicReferenceDemo
 * @description 原子引用
 * @create 2020-03-23 12:57
 **/
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("z3",22);
        User li4 = new User("li4",25);

        AtomicReference<User> reference = new AtomicReference<>();
        reference.set(z3);
        System.out.println(reference.compareAndSet(z3, li4) + "\t" + reference.get().toString());
        System.out.println(reference.compareAndSet(z3, li4) + "\t" + reference.get().toString());
    }
}
