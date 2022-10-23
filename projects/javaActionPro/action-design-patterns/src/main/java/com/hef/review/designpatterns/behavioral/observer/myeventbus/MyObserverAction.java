package com.hef.review.designpatterns.behavioral.observer.myeventbus;


import static com.google.common.base.Preconditions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 用来表示 @MySubscribe 注解方法
 * @Date 2022/10/23
 * @Author lifei
 */
public class MyObserverAction {

    // 观察者对象
    private Object target;
    // 方法
    private Method method;

    public MyObserverAction(Object target, Method method) {
        this.target = checkNotNull(target);
        this.method = method;
        this.method.setAccessible(true);
    }

    /**
     * event是method方法的参数
     * @param event
     */
    public void execute(Object event) {
        try {
            method.invoke(target, event);
        } catch (IllegalAccessException | InvocationTargetException  e) {
            throw new RuntimeException(e);
        }
    }
}
