package com.hef.review.designpatterns.behavioral.chainofresponsibility.impl02;

/**
 *
 * @Date 2022/10/23
 * @Author lifei
 */
public class HandlerB implements IHandler{
    @Override
    public boolean handle() {
        boolean handled = false;
        // ...... 判断当前处理其是否能处理，如果能处理返回ture，否则，返回false
        return handled;
    }
}
