package com.hef.review.designpatterns.structural.decorator;

/**
 * 一个咖啡品牌店
 * @Date 2022/10/16
 * @Author lifei
 */
public abstract class Beverage {

    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
