package com.hef.review.designpatterns.behavioral.command;

public interface Command {

    void execute();

    // 添加一个撤销功能
    default void undo(){}
}
