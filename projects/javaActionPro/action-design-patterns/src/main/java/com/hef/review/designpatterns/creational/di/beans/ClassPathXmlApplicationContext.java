package com.hef.review.designpatterns.creational.di.beans;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * XML DI容器上下文
 * @Date 2022/10/12
 * @Author lifei
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private BeansFactory beansFactory;
    private BeanConfigParser beanConfigParser;

    public ClassPathXmlApplicationContext(String configLocation) {
        this.beansFactory = new BeansFactory();
        this.beanConfigParser = new BeanXmlConfigParser();
        loadBeanDefinitions(configLocation);
    }

    /**
     * 根据配置文件路径，把XML文件 解析成 bean定义对象
     * @param configLocation
     */
    public void loadBeanDefinitions(String configLocation) {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(configLocation)) {
            if (in==null) {
                throw new RuntimeException("未发现配置文件:" + configLocation);
            }
            List<BeanDefinition> beanDefinitionList = beanConfigParser.parse(in);
            beansFactory.addBeanDefinitions(beanDefinitionList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从Bean工厂获取Bean对象
     * @param beanId
     * @return
     */
    @Override
    public Object getBean(String beanId) {
        return beansFactory.getBean(beanId);
    }
}
