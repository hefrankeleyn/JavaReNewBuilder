package com.hef.review.designpatterns.creational.di;

/**
 * DI容器接口
 * @Date 2022/10/12
 * @Author lifei
 */
public interface ApplicationContext {
    Object getBean(String beanId);

    void loadBeanDefinitions(String configLocation);
}
