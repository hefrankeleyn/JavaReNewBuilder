package com.hef.review.designpatterns.behavioral.state.version02;

/**
 * 状态模式测试
 * @Date 2022/10/25
 * @Author lifei
 */
public class GumballMachineTestDrive {

    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(5);

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

    }
}
