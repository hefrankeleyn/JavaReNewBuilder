package com.hef.review.designpatterns.behavioral.state.version01;

/**
 * 糖果售货机
 * @Date 2022/10/25
 * @Author lifei
 */
public class GumballMachine {

    // 售罄
    private final static int SOLD_OUT = 0;
    // 没有25分钱
    private final static int NO_QUARTER = 1;
    //有25分
    private final static int HAS_QUARTER = 2;
    // 售出糖果
    private final static int SOLD = 3;

    private int state = SOLD_OUT;

    private int count;
    public GumballMachine(int count) {
        this.count = count;
        if (count>0) {
            state = NO_QUARTER;
        }
    }

    /**
     * 投入硬币
     */
    public void insertQuarter() {
        if (state == HAS_QUARTER) { //已经有硬币了
            System.out.println("你不用投入另一个硬币");
        } else if (state == SOLD_OUT) { // 售罄
            System.out.println("你不能投入硬币，因为这个机器的糖果销售完了");
        } else if (state == SOLD) {
            System.out.println("请等待，我们将给你一个糖果");
        } else if (state == NO_QUARTER) {
            state = HAS_QUARTER;
            System.out.println("你投入了一个硬币");
        }
    }

    /**
     * 退回硬币
     */
    public void ejectQuarter() {
        if (state == HAS_QUARTER) {
            System.out.println("硬币退回");
            state = NO_QUARTER;
        } else if (state == NO_QUARTER) {
            System.out.println("你还没有投入硬币");
        } else if (state == SOLD) {
            System.out.println("糖果已经销售给你了");
        } else if (state == SOLD_OUT) {
            System.out.println("不能退，因为你没有投入硬币");
        }
    }

    /**
     * 转动曲柄
     */
    public void turnCrank() {
        if (state == SOLD) {
            System.out.println("转动两次，不能给你另一个糖果");
        } else if (state == NO_QUARTER) {
            System.out.println("你转动了曲柄，但是你还没有投入硬币");
        } else if (state == SOLD_OUT) {
            System.out.println("你转动了曲柄，但是这里没有糖果");
        } else if (state == HAS_QUARTER) {
            System.out.println("你转动了......");
            state = SOLD;
            dispense();
        }
    }

    /**
     * 发放糖果
     */
    private void dispense() {
        if (state == SOLD) {
            System.out.println("你个糖果是弹出");
            count = count-1;
            if (count==0) {
                System.out.println("售罄了！");
                state = SOLD_OUT;
            } else {
                state = NO_QUARTER;
            }
        } else if (state == NO_QUARTER) {
            System.out.println("你需要先付费！");
        } else if (state == SOLD_OUT) {
            System.out.println("没有糖果可以弹出");
        } else if (state == HAS_QUARTER) {
            System.out.println("没有糖果售出");
        }
    }
}
