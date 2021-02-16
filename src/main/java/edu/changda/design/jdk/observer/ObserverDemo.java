package edu.changda.design.jdk.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * {@link java.util.Observer} 示例
 *
 * @author Linn-cn
 * @create 2020/11/24
 */
public class ObserverDemo {
    public static void main(String[] args) {
        EventObservable observable = new EventObservable();
        // 添加观察者(监听者)
        observable.addObserver(new EventObServer());
        // 发布消息(事件)
        observable.notifyObservers("Hello,World");
    }

    static class EventObservable extends Observable{

        @Override
        public void notifyObservers(Object arg) {
            super.setChanged();
            super.notifyObservers(arg);
            super.clearChanged();
        }
    }

    static class EventObServer implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            System.out.println("收到事件：" + arg);
        }
    }
}
