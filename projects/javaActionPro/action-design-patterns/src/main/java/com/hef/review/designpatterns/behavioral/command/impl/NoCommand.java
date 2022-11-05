package com.hef.review.designpatterns.behavioral.command.impl;

import com.hef.review.designpatterns.behavioral.command.Command;

/**
 * 不做任何功能的按钮
 *  很多时候，空对象本身也被视为一种设计模式
 * @Date 2022/11/5
 * @Author lifei
 */
public class NoCommand implements Command {
    @Override
    public void execute() {
    }
}
