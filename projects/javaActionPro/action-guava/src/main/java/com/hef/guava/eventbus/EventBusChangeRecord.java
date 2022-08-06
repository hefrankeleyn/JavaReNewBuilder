package com.hef.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * 监听者
 * @Date 2022/8/6
 * @Author lifei
 */
public class EventBusChangeRecord {

    /**
     * 订阅消息
     * @param changeEvent
     */
    @Subscribe
    public void recordCustomerChange(ChangeEvent changeEvent) {
        System.out.println("Listener: " + changeEvent.getData());
    }
}
