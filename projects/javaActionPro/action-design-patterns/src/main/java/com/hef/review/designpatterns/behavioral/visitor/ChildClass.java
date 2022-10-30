package com.hef.review.designpatterns.behavioral.visitor;

/**
 * @Date 2022/10/30
 * @Author lifei
 */
public class ChildClass extends ParentClass{

    @Override
    public void f() {
        System.out.println("I'm ChildClass's f()");
    }
}
