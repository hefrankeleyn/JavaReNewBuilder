package com.hef.review.designpatterns.behavioral.observer.evenbus;

import com.google.common.eventbus.Subscribe;
import com.hef.review.designpatterns.behavioral.observer.myeventbus.MySubscribe;

/**
 * 一个观察者：使用了Guava的@Subscribe 注解
 * @Date 2022/10/22
 * @Author lifei
 */
public class CurrentConditionsDisplayListener{

    /**
     * 添加一个订阅主题的竹梅
     */
    @Subscribe
    public void display(WeatherDataEvent weatherDataEvent) {
        System.out.println("Current conditions: " + weatherDataEvent.getTemperature() + "F degrees and "
                + weatherDataEvent.getHumidity() + "% humidity");
    }

    /**
     *
     * @param temperature
     */
    @Subscribe
    public void display(Float temperature) {
        System.out.println("Current conditions: " + temperature + "F degrees and "
                + 0.1 + "% humidity");
    }


    /**
     * 使用自定义的eventbus
     * @param temperature
     */
    @MySubscribe
    public void displaySelf(Float temperature) {
        System.out.println("Self： Current conditions: " + temperature + "F degrees and "
                + 0.1 + "% humidity");
    }
}
