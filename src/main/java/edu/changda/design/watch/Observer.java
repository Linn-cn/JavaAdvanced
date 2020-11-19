package edu.changda.design.watch;

/**
 * @program: Designer
 * @classname: Observer
 * @description: 观察者接口
 * @author: 朱林
 * @create: 2020-01-04 18:48
 **/
public interface Observer {
    /**
     * 相应的动作
     * @param message
     */
    void update(String message);
}
