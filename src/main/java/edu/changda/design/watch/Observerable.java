package edu.changda.design.watch;

/**
 * @program: Designer
 * @classname: Observerable
 * @description: 被观察者 也即目标
 * @author: 朱林
 * @create: 2020-01-04 18:48
 **/
public interface Observerable {
    /**
     * 注册观察者
     * @param o
     */
    void registerObserver(Observer o);

    /**
     * 删除观察者
     * @param o
     */
    void removeObserver(Observer o);

    /**
     * 通知
     */
    void notifyObserver();
}
