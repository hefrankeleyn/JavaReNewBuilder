package com.hef.review.designpatterns.behavioral.command.impl;

import com.hef.review.designpatterns.behavioral.command.Command;
import com.hef.review.designpatterns.behavioral.command.bean.CeilingFan;

/**
 * 电扇命令
 * @Date 2022/11/5
 * @Author lifei
 */
public class CeilingFanOFFCommand implements Command {

    private CeilingFan ceilingFan;
    private int prevSpeed;

    public CeilingFanOFFCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        // 记录当前的速度，以便撤销的时候使用
        this.prevSpeed = ceilingFan.getSpeed();
        ceilingFan.off();
        System.out.println("关闭电扇");
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
