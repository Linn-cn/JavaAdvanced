package com.changda.design.watch;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Designer
 * @classname: WechatServer
 * @description: 目标对象
 * @author: 朱林
 * @create: 2020-01-04 18:53
 **/
public class WechatServer implements Observerable {
    /**
     * 观察者集合
     */
    private List<Observer> observers = new ArrayList<>();

    /**
     * 消息
     */
    private String msg;

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (!observers.isEmpty()) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObserver() {
        observers.forEach(o -> o.update(msg));
    }

    public void setInfomation(String s) {
        this.msg = s;
        System.out.println("微信服务更新消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }
}
