package com.hef.review.designpatterns.structural.decorator;

/**
 * 添加摩卡配料
 * @Date 2022/10/16
 * @Author lifei
 */
public class Mocha extends CondimentDecorator{

    public Beverage beverage;
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public double cost() {
        return 0.20 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }
}
