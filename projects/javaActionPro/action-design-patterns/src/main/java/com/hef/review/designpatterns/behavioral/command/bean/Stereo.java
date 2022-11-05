package com.hef.review.designpatterns.behavioral.command.bean;

/**
 * 音响
 * @Date 2022/11/5
 * @Author lifei
 */
public class Stereo {

    private String name;

    public Stereo(String name) {
        this.name = name;
    }

    // 音量
    private int volume;

    public void on() {
        System.out.println(name + " 打开音响...");
    }

    public void setCD() {
        System.out.println(name + " 选择CD");
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println(name + " 将音量设置为: " + volume);
    }

    public void off() {
        System.out.println("关闭音响");
    }
}
