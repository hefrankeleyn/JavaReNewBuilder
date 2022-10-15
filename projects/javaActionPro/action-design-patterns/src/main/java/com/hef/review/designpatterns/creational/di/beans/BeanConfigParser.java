package com.hef.review.designpatterns.creational.di.beans;

import java.io.InputStream;
import java.util.List;

/**
 * 将XML配置解析成 Bean定义
 * @Date 2022/10/12
 * @Author lifei
 */
public interface BeanConfigParser {
    /**
     * 解析Bean定义
     * @param in
     * @return
     */
    List<BeanDefinition> parse(InputStream in);
}
