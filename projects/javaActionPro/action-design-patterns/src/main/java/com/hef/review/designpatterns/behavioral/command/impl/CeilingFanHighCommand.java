package com.hef.review.designpatterns.behavioral.command.impl;

import com.hef.review.designpatterns.behavioral.command.Command;
import com.hef.review.designpatterns.behavioral.command.bean.CeilingFan;

/**
 * 电扇命令
 * @Date 2022/11/5
 * @Author lifei
 */
public class CeilingFanHighCommand implements Command {

    private CeilingFan ceilingFan;
    private int prevSpeed;

    public CeilingFanHighCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        // 记录当前的速度，以便撤销的时候使用
        this.prevSpeed = ceilingFan.getSpeed();
        ceilingFan.high();
        System.out.println("设置成高档位");
    }

    @Override
    public void undo() {
        if (prevSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if (prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if (prevSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if (prevSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
    }
}
