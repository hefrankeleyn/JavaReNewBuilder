package com.hef.review.designpatterns.structural.proxy.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * 演示CGLIB
 * @Date 2022/10/17
 * @Author lifei
 */
public class DemoCGLIB {

    public static void main(String[] args) {
        TargetObject target = new TargetObject();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        enhancer.setCallback(new TargetInterceptor(target));

        TargetObject targetObject = (TargetObject) enhancer.create();
        targetObject.a();
    }
}
