package com.hef.review.designpatterns.behavioral.chainofresponsibility.impl02;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器链的第二种实现方案：使用数组来存放处理器
 * @Date 2022/10/23
 * @Author lifei
 */
public class HandlerChain {
    private List<IHandler> handlers = new ArrayList<>();

    public void addHandler(IHandler handler) {
        this.handlers.add(handler);
    }

    public void handle() {
        for (IHandler handler : handlers) {
            boolean handled = handler.handle();
            if (handled) {
                break;
            }
        }
    }
}
