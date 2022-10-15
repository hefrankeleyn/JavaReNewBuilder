package com.hef.review.designpatterns.creational.di;

import org.apache.commons.collections4.CollectionUtils;

import static com.google.common.base.Preconditions.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean工厂：利用反射， 根据Bean定义创建Bean对象
 * @Date 2022/10/12
 * @Author lifei
 */
public class BeansFactory {

    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();

    /**
     * 向Bean工厂中，添加Bean定义
     * @param beanDefinitionList
     */
    public void addBeanDefinitions(List<BeanDefinition> beanDefinitionList) {
        for (BeanDefinition beanDefinition : beanDefinitionList) {
            this.beanDefinitions.putIfAbsent(beanDefinition.getId(), beanDefinition);
        }

        for (BeanDefinition beanDefinition : beanDefinitionList) {
            if (!beanDefinition.isLazyInit() && beanDefinition.isSingleton()) {
                singletonObjects.put(beanDefinition.getId(), createBean(beanDefinition));
            }
        }
    }

    /**
     * 根据beanId获取Bean对象
     * @param beanId
     * @return
     */
    public Object getBean(String beanId) {
        BeanDefinition beanDefinition = beanDefinitions.get(beanId);
        checkState(Objects.nonNull(beanDefinition), "Bean is not defined:" + beanId);

        return createBean(beanDefinition);
    }


    /**
     * 根据Bean定义创建Bean对象
     * @param beanDefinition
     * @return
     */
    private Object createBean(BeanDefinition beanDefinition) {
        if (beanDefinition.isSingleton() && singletonObjects.containsKey(beanDefinition.getId())) {
            return singletonObjects.get(beanDefinition.getId());
        }
        Object result = null;
        try {
            Class<?> beanClass = Class.forName(beanDefinition.getClassName());
            List<BeanDefinition.ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
            if (CollectionUtils.isEmpty(constructorArgs)) {
                result =  beanClass.newInstance();
            } else {
                Class[] argClasses = new Class[constructorArgs.size()];
                Object[] argObjects = new Object[constructorArgs.size()];
                for (int k = 0; k < constructorArgs.size(); k++) {
                    BeanDefinition.ConstructorArg arg = constructorArgs.get(k);
                    if (!arg.isRef()) {
                        argClasses[k] = arg.getType();
                        argObjects[k] = arg.getArg();
                    } else {
                        BeanDefinition refBeanDefinition = beanDefinitions.get(arg.getArg());
                        checkState(Objects.nonNull(refBeanDefinition), "Bean is not defined: " + arg.getArg());
                        argClasses[k] = Class.forName(refBeanDefinition.getClassName());
                        argObjects[k] = createBean(refBeanDefinition);
                    }
                }
                result = beanClass.getConstructor(argClasses).newInstance(argObjects);
            }
            if (Objects.nonNull(result) && beanDefinition.isSingleton()) {
                singletonObjects.putIfAbsent(beanDefinition.getId(), result);
                return singletonObjects.get(beanDefinition.getId());
            }
            return result;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
