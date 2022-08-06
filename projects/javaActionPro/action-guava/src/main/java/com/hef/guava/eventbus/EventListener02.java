package com.hef.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @Date 2022/8/6
 * @Author lifei
 */
public class EventListener02 {

    @Subscribe
    public void run(ChangeEvent changeEvent) {
        System.out.println("run: "+changeEvent.getData());
    }
}
