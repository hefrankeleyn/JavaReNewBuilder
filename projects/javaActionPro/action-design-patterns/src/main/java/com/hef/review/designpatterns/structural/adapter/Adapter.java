package com.hef.review.designpatterns.structural.adapter;

/**
 * @Date 2022/10/19
 * @Author lifei
 */
public class Adapter extends Adaptee implements ITarget {
    @Override
    public void a2() {
        super.a1();
    }

    @Override
    public void b2() {
        super.b1();
    }
}
