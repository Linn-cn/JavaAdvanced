package edu.changda.design.singleton;

public class Demo {
  public static void main(String[] args) {
    SingletonObject a = SingletonEnum.INSTANCE.getInstance();
    SingletonObject b = SingletonEnum.INSTANCE.getInstance();
    System.out.println(a == b);
  }
}
