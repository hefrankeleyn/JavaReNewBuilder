package com.hef.review.designpatterns.behavioral.observer.myeventbus;

import java.util.concurrent.Executor;

/**
 * 异步非阻塞的EventBus
 * @Date 2022/10/23
 * @Author lifei
 */
public class MySyncEventBus extends MyEventBus {

    public MySyncEventBus(Executor executor) {
        super(executor);
    }
}
