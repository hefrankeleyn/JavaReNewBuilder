package com.hef.review.designpatterns.behavioral.chainofresponsibility.impl01;

/**
 * 处理器链：
 *  从数据结构的角度来看，它就是一个记录了链头、链尾的链表。
 *  其中，记录链尾，是为了方便添加处理器
 * @Date 2022/10/23
 * @Author lifei
 */
public class HandlerChain {

    private Handler head, tail;
    /**
     * 添加处理器
     * @param handler
     */
    public void addHandler(Handler handler) {
        handler.setSuccessor(null);
        if (head==null) {
            head = handler;
            tail = handler;
            return;
        }
        tail.setSuccessor(handler);
        tail = handler;
    }

    public void handle() {
        if (head!=null) {
            head.handle();
        }
    }
}
