package com.hef.review.designpatterns.behavioral.observer.impl;

import com.hef.review.designpatterns.behavioral.observer.Observer;
import com.hef.review.designpatterns.behavioral.observer.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个天气数据主题
 * @Date 2022/10/22
 * @Author lifei
 */
public class WeatherDataSubject implements Subject {
    private List<Observer> observerList = new ArrayList<>();
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int i = observerList.indexOf(observer);
        if (i>=0) {
            observerList.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update(temperature, humidity, pressure);
        }
    }

    /**
     * 改变主题的状态，并通知观察者
     * @param temperature
     * @param humidity
     * @param pressure
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    /**
     * 通知观察者
     */
    private void measurementsChanged() {
        notifyObservers();
    }
}
