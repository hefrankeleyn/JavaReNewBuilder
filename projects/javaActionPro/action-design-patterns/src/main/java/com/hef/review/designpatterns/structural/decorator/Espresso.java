package com.hef.review.designpatterns.structural.decorator;

/**
 * 某一个饮品
 * @Date 2022/10/16
 * @Author lifei
 */
public class Espresso extends Beverage{

    public Espresso() {
        this.description = "Espresso";
    }
    @Override
    public double cost() {
        return 0.89;
    }
}
