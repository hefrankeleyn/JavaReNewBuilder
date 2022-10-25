package com.hef.review.designpatterns.behavioral.state.version02;

/**
 * 销售的状态
 * @Date 2022/10/25
 * @Author lifei
 */
public class GumballMachineSoldState implements GumballMachineState{

    private GumballMachine gumballMachine;

    public GumballMachineSoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("硬币已经投过了，不需要再次投入硬币。请等待，我们将给你一个糖果!");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("不能退出硬币，应为你已经转动了转轴！");
    }

    @Override
    public void turnCrank() {
        System.out.println("你又转动了转轴，但是不会产生更多的糖果......");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount()==0) {
            System.out.println("哦，售罄了！");
            gumballMachine.setGumballMachineState(gumballMachine.getSoldOutState());
        } else {
            gumballMachine.setGumballMachineState(gumballMachine.getNoQuarterState());
        }
    }
}
