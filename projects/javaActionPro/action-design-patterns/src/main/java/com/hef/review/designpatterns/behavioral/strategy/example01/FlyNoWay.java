package com.hef.review.designpatterns.behavioral.strategy.example01;

/**
 * 一个具体fly的行为
 * @Date 2022/10/23
 * @Author lifei
 */
public class FlyNoWay implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("FlyNoWay: fly...0");
    }
}
