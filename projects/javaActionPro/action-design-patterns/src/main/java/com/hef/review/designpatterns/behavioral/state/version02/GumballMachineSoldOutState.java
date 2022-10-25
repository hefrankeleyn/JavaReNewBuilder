package com.hef.review.designpatterns.behavioral.state.version02;


/**
 * 售罄的状态
 * @Date 2022/10/25
 * @Author lifei
 */
public class GumballMachineSoldOutState implements GumballMachineState{

    private GumballMachine gumballMachine;

    public GumballMachineSoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("已经售罄，不能投入硬币");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("已经售罄，没有投入硬币!");
    }

    @Override
    public void turnCrank() {
        System.out.println("你又转动了转轴，已经售罄了！");
    }

    @Override
    public void dispense() {
        System.out.println("已经售罄了，不能售出糖果");
    }
}
