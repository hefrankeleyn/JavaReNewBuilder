package com.hef.review.designpatterns.behavioral.visitor;

/**
 * @Date 2022/10/30
 * @Author lifei
 */
public class ApplicationDemo {

    public static void main(String[] args) {
        SingleDispatchClass demo = new SingleDispatchClass();
        ParentClass p = new ChildClass();
        // I'm ChildClass's f()
        demo.polymorphismFunction(p);//执行哪个对象的方法，由对象的实际类型决定
        // I am overloadFunction(ParentClass p).
        demo.overloadFunction(p);//执行对象的哪个方法，由参数对象的声明类型决定
    }
}
