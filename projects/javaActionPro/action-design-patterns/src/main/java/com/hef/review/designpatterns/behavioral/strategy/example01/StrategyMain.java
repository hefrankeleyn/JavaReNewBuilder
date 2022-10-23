package com.hef.review.designpatterns.behavioral.strategy.example01;

/**
 * @Date 2022/10/23
 * @Author lifei
 */
public class StrategyMain {

    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.performFly();
        duck.setFlyBehavior(new FlyWithWings());
        duck.performFly();
    }
}
