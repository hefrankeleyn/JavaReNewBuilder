package com.hef.review.designpatterns.behavioral.chainofresponsibility.impl01;

import java.util.Objects;

/**
 * 职责链模式：第一种实现方案
 * 定义一个抽象的Handler，handler 是抽象方法。
 *     1. 每个处理器 handle() 函数的代码结构类似。
 *     2. 如果它能处理请求，就bu不继续向下传递；
 *     3. 如果它不能处理，则由后面的处理器来处理（也就是调用 successor.handler() 来处理)
 * @Date 2022/10/23
 * @Author lifei
 */
public abstract class Handler {

    protected Handler successor = null;

//    public abstract void handle();

    public final void handle() {
        boolean handled = doHandle();
        if (!handled && Objects.nonNull(successor)) {
            successor.handle();
        }
    }

    protected abstract boolean doHandle();

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
}
