package com.hef.review.designpatterns.structural.proxy.dynamicproxy.jdk;

/**
 * 主题的一个实现
 * @Date 2022/10/17
 * @Author lifei
 */
public class PersonBeanImpl implements PersonBean{
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
