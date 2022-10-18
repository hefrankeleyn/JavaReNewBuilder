package com.hef.review.designpatterns.structural.proxy.dynamicproxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 方法拦截
 * @Date 2022/10/17
 * @Author lifei
 */
public class TargetInterceptor implements MethodInterceptor {

    private TargetObject targetObject;

    public TargetInterceptor(TargetObject targetObject) {
        this.targetObject = targetObject;
    }

    /**
     *
     * @param o 目标对象
     * @param method 目标对象方法
     * @param params 目标对象方法参数
     * @param methodProxy CGLIB代理方法对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        System.out.println("方法调用前");
        // invoke 与 invokeSuper 的区别： https://www.cnblogs.com/lvbinbin2yujie/p/10284316.html
        // invoke 调用的对象是没有增强过，invokeSuper调用的雕像是增强过
//        Object result = methodProxy.invokeSuper(o, params);
//        Object result = methodProxy.invokeSuper(targetObject, params);  // 会报错
        Object result = methodProxy.invoke(targetObject, params);
//        Object result = methodProxy.invoke(o, params);  // 获报错
        System.out.println("方法调用后");
        return result;
    }
}
