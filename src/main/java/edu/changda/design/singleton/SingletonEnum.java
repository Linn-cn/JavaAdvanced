package edu.changda.design.singleton;

public enum SingletonEnum {

  /**
   * 单例对象
   */
  INSTANCE;

  private final SingletonObject instance;

  SingletonEnum(){
    instance = new SingletonObject();
  }

  public SingletonObject getInstance(){
    return instance;
  }

}
