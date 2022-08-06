package com.hef.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @Date 2022/8/6
 * @Author lifei
 */
public class EventBusDemo {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        // 注册监听者
        EventListener02 eventListener02 = new EventListener02();
        eventBus.register(new EventBusChangeRecord());
        eventBus.register(eventListener02);
        eventBus.unregister(eventListener02);

        // 下面的代码必须在是最后调用 post()  方法
        ChangeEvent changeEvent = new ChangeEvent();
        // 改变主题的属性
        changeEvent.changeData("hello, EventBus");
        // 发布主题
        eventBus.post(changeEvent);
        // 改变主题的属性
        changeEvent.changeData("hello, EventBus2");
        eventBus.post(changeEvent);
    }
}
