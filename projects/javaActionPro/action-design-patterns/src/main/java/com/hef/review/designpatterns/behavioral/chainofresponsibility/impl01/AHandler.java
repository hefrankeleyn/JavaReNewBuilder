package com.hef.review.designpatterns.behavioral.chainofresponsibility.impl01;

/**
 * 处理器A
 * @Date 2022/10/23
 * @Author lifei
 */
public class AHandler extends Handler{

    /*@Override
    public void handle() {
        boolean handled = false;
        //.... 判断当前处理其链是否可以操作，如果可以操作将 handled设置为true
        if (!handled && successor!=null) {
            successor.handle();
        }
    }*/

    @Override
    protected boolean doHandle() {
        boolean handled = false;
        // 看是否能在当前处理器处理，如果能成功处理，返回true，否则返回false。
        return handled;
    }
}
