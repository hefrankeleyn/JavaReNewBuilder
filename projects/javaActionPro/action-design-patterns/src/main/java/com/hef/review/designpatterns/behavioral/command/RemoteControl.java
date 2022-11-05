package com.hef.review.designpatterns.behavioral.command;

import com.google.common.base.MoreObjects;
import com.hef.review.designpatterns.behavioral.command.impl.NoCommand;

/**
 * 遥控板上有多个按钮，每个按钮都对应一个命令
 * @Date 2022/11/5
 * @Author lifei
 */
public class RemoteControl {

    // 启动按钮
    private Command[] onCommands;
    // 关闭按钮
    private Command[] offCommands;
    // 为遥控起添加一个撤销按钮
    private Command undoCommand;

    public RemoteControl() {
        int num = 7;
        // 定义两排按钮，七个启动开关的按钮，七个关闭的按钮
        this.onCommands = new Command[num];
        this.offCommands = new Command[num];
        // 对两排安妮进行初始化，初始化一个不做任何功能的
        NoCommand noCommand = new NoCommand();
        for (int i = 0; i < num; i++) {
            this.onCommands[i] = noCommand;
            this.offCommands[i] = noCommand;
        }
        // 撤销按钮初始化
        this.undoCommand = noCommand;
    }

    /**
     * 设置按钮功能
     * @param slot
     * @param onCommand
     * @param offCommand
     */
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        this.onCommands[slot] = onCommand;
        this.offCommands[slot] = offCommand;
    }

    /**
     * 开启按钮是被按下
     * @param slot
     */
    public void onButtonWasPushed(int slot) {
        this.onCommands[slot].execute();
        this.undoCommand = this.onCommands[slot];
    }

    /**
     * 关闭按钮是被按下
     * @param slot
     */
    public void offButtonWasPushed(int slot) {
        this.offCommands[slot].execute();
        this.undoCommand = this.offCommands[slot];
    }

    /**
     * 按下撤销按钮
     */
    public void undoButtonWasPushed() {
        this.undoCommand.undo();
    }

    /**
     * 打印遥控器功能
     * @return
     */
    @Override
    public String toString() {
        MoreObjects.ToStringHelper toStringHelper = MoreObjects.toStringHelper(RemoteControl.class);
        for (int i = 0; i < onCommands.length; i++) {
            toStringHelper.add("[slot " + i + "] " + onCommands[i].getClass().getName(),
                    offCommands[i].getClass().getName());
        }
        return toStringHelper.toString();
    }
}
