package com.hef.review.designpatterns.behavioral.observer.impl;

import com.hef.review.designpatterns.behavioral.observer.DisplayElement;
import com.hef.review.designpatterns.behavioral.observer.Observer;
import com.hef.review.designpatterns.behavioral.observer.Subject;

/**
 * 一个观察者实现： 通过构造函数把自己注册进一个主题
 * @Date 2022/10/22
 * @Author lifei
 */
public class CurrentConditionsDisplayObserver implements Observer, DisplayElement {

    private Subject weatherData;
    private float temperature;
    private float humidity;

    // 通过构造函数，把自己注册进一个主题
    public CurrentConditionsDisplayObserver(Subject subject) {
        this.weatherData = subject;
        subject.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }
}
