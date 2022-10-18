package com.hef.review.designpatterns.structural.proxy.dynamicproxy.cglib;

/**
 * 目标类
 * @Date 2022/10/17
 * @Author lifei
 */
public class TargetObject {
    public void a() {
        System.out.println("方法a 执行了");
        b();
    }
    public void b() {
        System.out.println("方法b执行了");
    }
}
