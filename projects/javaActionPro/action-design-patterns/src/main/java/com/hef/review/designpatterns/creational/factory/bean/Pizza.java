package com.hef.review.designpatterns.creational.factory.bean;

/**
 * @Date 2022/10/7
 * @Author lifei
 */
public class Pizza {

    public void prepare() {
        System.out.println("prepare...");
    }

    public void bake() {
        System.out.println("bake...");
    }

    public void cut() {
        System.out.println("cut...");
    }

    public void box() {
        System.out.println("box....");
    }
}
