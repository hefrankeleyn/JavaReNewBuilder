package com.hef.review.designpatterns.behavioral.observer;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.hef.review.designpatterns.behavioral.observer.evenbus.CurrentConditionsDisplayListener;
import com.hef.review.designpatterns.behavioral.observer.evenbus.WeatherDataEvent;
import com.hef.review.designpatterns.behavioral.observer.impl.CurrentConditionsDisplayObserver;
import com.hef.review.designpatterns.behavioral.observer.impl.WeatherDataSubject;
import com.hef.review.designpatterns.behavioral.observer.jdk.CurrentConditionsDisplay;
import com.hef.review.designpatterns.behavioral.observer.jdk.WeatherDataObservable;
import com.hef.review.designpatterns.behavioral.observer.myeventbus.MyEventBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 气象站， 测试观察者
 * @Date 2022/10/22
 * @Author lifei
 */
public class WeatherStation {

    public static void main(String[] args) {

        // 自定义观察者模式
//        selfObserverPattern();

        // jdk 观察者模式
//        jdkObserverPattern();

        // guava 的EventBus
//        guavaEventBus();

        // 自定义的EventBus
        MyEventBus myEventBus = new MyEventBus();
        // 注册一个观察者
        myEventBus.register(new CurrentConditionsDisplayListener());
        // 向观察者发送消息
        myEventBus.post(23.0f);

    }

    private static void guavaEventBus() {
        // 定义一个EventBus
//        EventBus eventBus = new EventBus(); // 同步阻塞模式
        final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20; // 异步非阻塞线程池大小
        // 异步非阻塞的方式
        ExecutorService executorService = Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE);
        EventBus eventBus = new AsyncEventBus(executorService);

        // 注册一个观察者
        eventBus.register(new CurrentConditionsDisplayListener());

        // 创建一个主题
        WeatherDataEvent weatherDataEvent = new WeatherDataEvent();
        weatherDataEvent.setMeasurements(20.1f, 0.3f, 105.1f);
        // 发布主题（会通知观察者）
//        eventBus.post(weatherDataEvent);
        eventBus.post(weatherDataEvent.getTemperature());
        // 关闭线程池
        executorService.shutdown();
    }

    /**
     * 自定义的观察者模式
     */
    private static void selfObserverPattern() {
        // 主题
        WeatherDataSubject weatherData = new WeatherDataSubject();
        // 一个观察者
        Observer observer = new CurrentConditionsDisplayObserver(weatherData);

        // 改变主题的状态，会触发更新观察者的内容
        weatherData.setMeasurements(20.1f, 0.3f, 105.1f);
    }

    /**
     * jdk 的观察者模式
     */
    private static void jdkObserverPattern() {
        // 可观察者
        WeatherDataObservable weatherDataObservable = new WeatherDataObservable();
        // 观者者
        java.util.Observer observer1 = new CurrentConditionsDisplay(weatherDataObservable);
        // 改变可观察者的状态
        weatherDataObservable.setMeasureMents(20.1f, 0.3f, 105.1f);
    }
}
