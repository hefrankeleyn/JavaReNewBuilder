package com.hef.review.designpatterns.behavioral.state.version02;

/**
 * 糖果收获机器
 * @Date 2022/10/25
 * @Author lifei
 */
public class GumballMachine {

    // 售罄状态
    private GumballMachineState soldOutState;
    // 没有投币状态
    private GumballMachineState noQuarterState;
    // 已经投币状体
    private GumballMachineState hasQuarterState;
    // 销售状态
    private GumballMachineState soldState;
    // 获奖状态
    private GumballMachineState winnerState;

    // 初始状态设置为 售罄状态
    private GumballMachineState gumballMachineState;
    private int count;

    public GumballMachine(int count) {
        this.soldOutState = new GumballMachineSoldOutState(this);
        this.noQuarterState = new GumballMachineNoQuarterState(this);
        this.hasQuarterState = new GumballMachineHasQuarterState(this);
        this.soldState = new GumballMachineSoldState(this);
        this.winnerState = new GumballMachineWinnerState(this);
        this.count = count;
        if (count>0) {
            gumballMachineState = this.noQuarterState;
        } else {
            gumballMachineState = this.soldOutState;
        }
    }

    public void insertQuarter() {
        gumballMachineState.insertQuarter();
    }

    public void ejectQuarter() {
        gumballMachineState.ejectQuarter();
    }

    public void turnCrank() {
        gumballMachineState.turnCrank();
        gumballMachineState.dispense();
    }

    public GumballMachineState getGumballMachineState() {
        return gumballMachineState;
    }

    public void setGumballMachineState(GumballMachineState gumballMachineState) {
        this.gumballMachineState = gumballMachineState;
    }

    public GumballMachineState getSoldOutState() {
        return soldOutState;
    }

    public GumballMachineState getNoQuarterState() {
        return noQuarterState;
    }

    public GumballMachineState getHasQuarterState() {
        return hasQuarterState;
    }

    public GumballMachineState getSoldState() {
        return soldState;
    }

    public GumballMachineState getWinnerState() {
        return winnerState;
    }

    public int getCount() {
        return count;
    }

    /**
     * 释放一个糖果
     */
    public void releaseBall() {
        System.out.println("一个糖果是滚出了售货机!");
        if (count!=0) {
            count -= 1;
        }
    }
}
