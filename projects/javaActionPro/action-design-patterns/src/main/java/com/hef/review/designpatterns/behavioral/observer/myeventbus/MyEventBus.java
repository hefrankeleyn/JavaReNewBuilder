package com.hef.review.designpatterns.behavioral.observer.myeventbus;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * 实现 同步阻塞的 EventBus
 * @Date 2022/10/23
 * @Author lifei
 */
public class MyEventBus {

    private Executor executor;
    private MyObserverRegister register = new MyObserverRegister();

    public MyEventBus() {
        // MoreExecutors.directExecutor() 是 Google Guava 提供的工具类，看似是多线程，实际上是单线程。
        // 之所以要这么实现，主要还是为了跟 AsyncEventBus 统一代码逻辑，做到代码复用
        this(MoreExecutors.directExecutor());
    }

    // 注意这里的修饰符
    protected MyEventBus(Executor executor) {
        this.executor = executor;
    }


    public void register(Object observer) {
        register.register(observer);
    }

    public void post(Object event) {
        List<MyObserverAction> observerActions = register.getMatchedMyObserverActions(event);
        for (MyObserverAction observerAction : observerActions) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    observerAction.execute(event);
                }
            });
        }
    }
}
