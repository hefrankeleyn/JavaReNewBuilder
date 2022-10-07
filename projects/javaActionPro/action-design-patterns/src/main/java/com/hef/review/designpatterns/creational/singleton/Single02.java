package com.hef.review.designpatterns.creational.singleton;

/**
 * 饿汉式：提前创建好（类加载的时候就执行）
 * @Date 2022/10/7
 * @Author lifei
 */
public class Single02 {

    private Single02(){}

    /**
     * 类加载的时候就会执行构造函数
     */
    private final static Single02 instance = new Single02();

    public static Single02 instance() {
        return instance;
    }
}
