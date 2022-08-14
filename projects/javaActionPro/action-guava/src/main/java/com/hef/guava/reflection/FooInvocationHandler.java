package com.hef.guava.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Date 2022/8/7
 * @Author lifei
 */
public class FooInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
