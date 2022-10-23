package com.hef.review.designpatterns.behavioral.strategy.example01;

/**
 * @Date 2022/10/23
 * @Author lifei
 */
public class MallardDuck extends Duck{

    public MallardDuck() {
        this.flyBehavior = new FlyNoWay();
    }

}
