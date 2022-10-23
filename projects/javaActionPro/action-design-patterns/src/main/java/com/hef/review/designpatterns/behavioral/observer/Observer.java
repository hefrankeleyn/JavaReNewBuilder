package com.hef.review.designpatterns.behavioral.observer;

/**
 * 观察者：当主题状态发生变化的时候，依赖该主题的观察者会自动更新
 * @Date 2022/10/21
 * @Author lifei
 */
public interface Observer {
    void update(float temp, float humidity, float pressure);
}
