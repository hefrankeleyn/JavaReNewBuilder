package com.hef.review.designpatterns.behavioral.observer.jdk;

import java.util.Observable;

/**
 * 可观察者
 * 继承 java.util.Observable
 * @Date 2022/10/22
 * @Author lifei
 */
public class WeatherDataObservable extends Observable {

    private float temperature;
    private float humidity;
    private float pressure;

    public void setMeasureMents(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    /**
     * 调用 notifyObservers() 之前，要先调用setChanged() 来指示状态已经改变
     * notifyObservers() 有一个重载方法 notifyObservers(args)
     */
    public void measurementsChanged() {
        // setChanged() 方法用来标记状态已经改变的事实，好让notifyObservers() 知道当它被调用时应该更新观察者。
        // 如果调用notifyObservers() 之前没有先调用 setChanged(), 观察者就不会被通知
        setChanged();
        // 不传递参数，观察者就需要从可观察者对象中"拉"（pull）数据
        notifyObservers();
        //如果传递参数，就是推送数据给观察者
//        Object args = new float[]{temperature, humidity, pressure};
//        notifyObservers(args);
    }

    // 观察者会利用下面三个方法获取WeatherData对象的状态
    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
