package com.hef.review.designpatterns.behavioral.state.version02;

import java.util.Random;

/**
 * 已经投币的状态
 * @Date 2022/10/25
 * @Author lifei
 */
public class GumballMachineHasQuarterState implements GumballMachineState{

    private Random randomWinner = new Random(System.currentTimeMillis());

    private GumballMachine gumballMachine;

    public GumballMachineHasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("你已经投过硬币了......");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("退出投入的硬币....");
        this.gumballMachine.setGumballMachineState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("转动了转轴....");
        int winner = randomWinner.nextInt(10);
        if (winner==0 && gumballMachine.getCount()>1) {
            System.out.println("恭喜，获奖了!");
            this.gumballMachine.setGumballMachineState(gumballMachine.getWinnerState());
        } else {
            this.gumballMachine.setGumballMachineState(gumballMachine.getSoldState());
        }
    }

    @Override
    public void dispense() {
        System.out.println("请转动转轴.....");
    }
}
