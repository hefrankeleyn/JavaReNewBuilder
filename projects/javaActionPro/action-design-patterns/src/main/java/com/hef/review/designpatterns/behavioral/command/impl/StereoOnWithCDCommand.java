package com.hef.review.designpatterns.behavioral.command.impl;

import com.hef.review.designpatterns.behavioral.command.Command;
import com.hef.review.designpatterns.behavioral.command.bean.Stereo;

/**
 * 音响打开的命令
 * @Date 2022/11/5
 * @Author lifei
 */
public class StereoOnWithCDCommand implements Command {

    private Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        this.stereo.on();
        this.stereo.setCD();
        this.stereo.setVolume(11);
    }

    @Override
    public void undo() {
        this.stereo.off();
    }
}
