package com.hef.guava.reflection;

import com.google.common.reflect.AbstractInvocationHandler;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.annotation.CheckForNull;
import java.lang.reflect.Method;

/**
 * @Date 2022/8/7
 * @Author lifei
 */
public class FooInvocationHandler02 extends AbstractInvocationHandler {
    @CheckForNull
    @Override
    protected Object handleInvocation(Object proxy, Method method, @Nullable Object[] args) throws Throwable {
        return null;
    }
}
