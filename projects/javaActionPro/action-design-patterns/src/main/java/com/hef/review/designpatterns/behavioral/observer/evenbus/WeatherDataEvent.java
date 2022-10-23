package com.hef.review.designpatterns.behavioral.observer.evenbus;

/**
 * 一个主题
 * @Date 2022/10/22
 * @Author lifei
 */
public class WeatherDataEvent {

    private float temperature;
    private float humidity;
    private float pressure;

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

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
