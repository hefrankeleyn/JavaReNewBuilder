package com.hef.guava.eventbus;

/**
 * @Date 2022/8/6
 * @Author lifei
 */
public class ChangeEvent {
    private String data;

    public void changeData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
