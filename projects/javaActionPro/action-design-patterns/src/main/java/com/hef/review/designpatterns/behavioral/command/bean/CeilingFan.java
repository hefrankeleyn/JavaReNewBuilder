package com.hef.review.designpatterns.behavioral.command.bean;

/**
 * 吊扇
 * @Date 2022/11/5
 * @Author lifei
 */
public class CeilingFan {

    public static final int HIGH = 3;   // 高
    public static final int MEDIUM = 2; // 中
    public static final int LOW = 1;    // 低
    public static final int OFF = 0;    // 关闭

    private String location;
    private int speed;  // 速度

    public CeilingFan(String location) {
        this.location = location;
        this.speed = OFF;
    }

    public void high() {
        this.speed = HIGH;
    }

    public void medium() {
        this.speed = MEDIUM;
    }

    public void low() {
        this.speed = LOW;
    }

    public void off() {
        this.speed = OFF;
    }

    /**
     * 获取当前吊扇的速度
     * @return
     */
    public int getSpeed() {
        return speed;
    }
}
