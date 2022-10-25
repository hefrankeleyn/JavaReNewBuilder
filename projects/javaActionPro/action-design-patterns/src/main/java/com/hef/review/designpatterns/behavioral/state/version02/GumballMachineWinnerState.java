package com.hef.review.designpatterns.behavioral.state.version02;


/**
 * 售罄的状态
 * @Date 2022/10/25
 * @Author lifei
 */
public class GumballMachineWinnerState implements GumballMachineState{

    private GumballMachine gumballMachine;

    public GumballMachineWinnerState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("你已经中奖了，不需要投入硬币，我们即将给你糖果");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("你已经中奖了，即将给你糖果，但是不能退出硬币给你。");
    }

    @Override
    public void turnCrank() {
        System.out.println("你转动了转轴，但是不会给你更多糖果。请稍等，会把奖励的糖果给你。");
    }

    @Override
    public void dispense() {
        System.out.println("你是获奖者，你能得到两个糖果");
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() == 0) {
            System.out.println("抱歉，售罄了，不能给你第二个糖果");
            gumballMachine.setGumballMachineState(gumballMachine.getSoldOutState());
        } else {
            System.out.println("哇！给你第二个糖果");
            gumballMachine.releaseBall();
            if (gumballMachine.getCount()==0) {
                System.out.println("售罄了!");
                gumballMachine.setGumballMachineState(gumballMachine.getSoldOutState());
            } else {
                gumballMachine.setGumballMachineState(gumballMachine.getNoQuarterState());
            }
        }
    }
}
