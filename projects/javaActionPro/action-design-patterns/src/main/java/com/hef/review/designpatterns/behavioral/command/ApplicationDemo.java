package com.hef.review.designpatterns.behavioral.command;

import com.hef.review.designpatterns.behavioral.command.bean.Light;
import com.hef.review.designpatterns.behavioral.command.bean.Stereo;
import com.hef.review.designpatterns.behavioral.command.impl.LightOffCommand;
import com.hef.review.designpatterns.behavioral.command.impl.LightOnCommand;
import com.hef.review.designpatterns.behavioral.command.impl.StereoOffCommand;
import com.hef.review.designpatterns.behavioral.command.impl.StereoOnWithCDCommand;

/**
 * @Date 2022/11/5
 * @Author lifei
 */
public class ApplicationDemo {

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light livingRoomLight = new Light("客厅的电灯");
        Stereo stereo = new Stereo("大成音响");

        // 电灯命令
        LightOnCommand lightOnCommand = new LightOnCommand(livingRoomLight);
        LightOffCommand lightOffCommand = new LightOffCommand(livingRoomLight);

        // 音响命令
        StereoOnWithCDCommand stereoOnWithCDCommand = new StereoOnWithCDCommand(stereo);
        StereoOffCommand stereoOffCommand = new StereoOffCommand(stereo);

        // 设置命令
        remoteControl.setCommand(0, lightOnCommand, lightOffCommand);
        remoteControl.setCommand(1, stereoOnWithCDCommand, stereoOffCommand);

        // 打印遥控板
        System.out.println(remoteControl);

        // 按下按钮
        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        // 测试撤销按钮
        remoteControl.undoButtonWasPushed();

        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        // 测试撤销按钮
        remoteControl.undoButtonWasPushed();
    }
}
