package com.hef.review.designpatterns.behavioral.state.version02;

/**
 * 糖果售卖机的状态
 */
public interface GumballMachineState {

    // 投入硬币
    void insertQuarter();

    // 退出硬币
    void ejectQuarter();

    // 转动手柄
    void turnCrank();

    // 售卖糖果
    void dispense();
}
