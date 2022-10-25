package com.hef.review.designpatterns.behavioral.state.version02;

/**
 * 没有硬币的状态
 * @Date 2022/10/25
 * @Author lifei
 */
public class GumballMachineNoQuarterState implements GumballMachineState{

    private GumballMachine gumballMachine;

    public GumballMachineNoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("你投入了一枚硬币.......");
        gumballMachine.setGumballMachineState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("还没有投入硬币......");
    }

    @Override
    public void turnCrank() {
        System.out.println("已经转动转轴，但是还没有投入硬币......");
    }

    @Override
    public void dispense() {
        System.out.println("你需要先投入硬币....");
    }
}
