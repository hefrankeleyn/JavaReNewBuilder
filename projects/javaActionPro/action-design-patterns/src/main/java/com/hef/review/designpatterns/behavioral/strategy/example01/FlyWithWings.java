package com.hef.review.designpatterns.behavioral.strategy.example01;

/**
 * 一个具体的行为
 * @Date 2022/10/23
 * @Author lifei
 */
public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("FlyWithWings: fly 1,2,3");
    }
}
