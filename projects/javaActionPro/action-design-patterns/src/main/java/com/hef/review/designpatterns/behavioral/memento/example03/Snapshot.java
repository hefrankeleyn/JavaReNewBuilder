package com.hef.review.designpatterns.behavioral.memento.example03;

/**
 * 一个快照类
 * @Date 2022/11/2
 * @Author lifei
 */
public class Snapshot {

    private final String text;
    public Snapshot(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
