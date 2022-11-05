package com.hef.review.designpatterns.behavioral.command.bean;

/**
 * 电灯，有开和关功能
 * @Date 2022/11/5
 * @Author lifei
 */
public class Light {

    private String name;

    public Light(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println(name + " 电灯打开");
    }

    public void off() {
        System.out.println(name + " 电灯关闭");
    }
}
