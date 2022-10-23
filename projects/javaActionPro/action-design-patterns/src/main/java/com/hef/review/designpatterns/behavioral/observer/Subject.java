package com.hef.review.designpatterns.behavioral.observer;

/**
 * 主题： 注册观察者、移除观察者，通知观察者
 * @Date 2022/10/21
 * @Author lifei
 */
public interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    // 当主题状态改变的时候，这个方法会被调用，以通知所有的观察者
    void notifyObservers();
}
