package com.hef.review.designpatterns.behavioral.visitor;

/**
 * 验证单分工
 * @Date 2022/10/30
 * @Author lifei
 */
public class SingleDispatchClass {

    public void polymorphismFunction(ParentClass p) {
        p.f();
    }

    public void overloadFunction(ParentClass p) {
//        p.f();
        System.out.println("I am overloadFunction(ParentClass p).");
    }

    public void overloadFunction(ChildClass c) {
//        c.f();
        System.out.println("I am overloadFunction(ChildClass c).");
    }
}
