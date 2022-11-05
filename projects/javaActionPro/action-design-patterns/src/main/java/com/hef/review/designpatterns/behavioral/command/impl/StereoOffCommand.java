package com.hef.review.designpatterns.behavioral.command.impl;

import com.hef.review.designpatterns.behavioral.command.Command;
import com.hef.review.designpatterns.behavioral.command.bean.Stereo;

/**
 * @Date 2022/11/5
 * @Author lifei
 */
public class StereoOffCommand implements Command {

    private Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        this.stereo.off();
    }

    @Override
    public void undo() {
        this.stereo.on();
        this.stereo.setCD();
        this.stereo.setVolume(11);
    }
}
