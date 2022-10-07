package com.hef.review.designpatterns.creational.singleton;

/**
 * @Date 2022/10/7
 * @Author lifei
 */
public class Single03 {

    private Single03() {}

    private static class InstanceHolder {
        private static final Single03 instance = new Single03();
    }

    /**
     * 只有在调用 instance() 的时候，才会把instance 创建出来
     * @return
     */
    public static Single03 instance() {
        return InstanceHolder.instance;
    }
}
