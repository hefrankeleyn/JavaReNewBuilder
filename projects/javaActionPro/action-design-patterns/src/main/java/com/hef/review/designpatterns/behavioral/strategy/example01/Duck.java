package com.hef.review.designpatterns.behavioral.strategy.example01;

/**
 * @Date 2022/10/23
 * @Author lifei
 */
public abstract class Duck {

    protected FlyBehavior flyBehavior;

    public void performFly() {
        flyBehavior.fly();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
}
