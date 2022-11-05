package com.hef.review.designpatterns.behavioral.command.impl;

import com.hef.review.designpatterns.behavioral.command.Command;
import com.hef.review.designpatterns.behavioral.command.bean.Light;

/**
 * 关闭开关的命令
 * @Date 2022/11/5
 * @Author lifei
 */
public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.off();
    }

    @Override
    public void undo() {
        this.light.on();
    }
}
