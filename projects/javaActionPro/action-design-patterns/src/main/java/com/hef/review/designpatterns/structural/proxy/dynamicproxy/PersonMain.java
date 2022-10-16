package com.hef.review.designpatterns.structural.proxy.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * @Date 2022/10/17
 * @Author lifei
 */
public class PersonMain {

    public static void main(String[] args) {
        PersonBean personBean = new PersonBeanImpl();
        personBean.setName("001");
        PersonBean ownerProxy = getOwnerProxy(personBean);
        System.out.println(ownerProxy.getName());
        ownerProxy.setName("002");
    }

    /**
     * 创建代理对象
     * @param personBean
     * @return
     */
    private static PersonBean getOwnerProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(), personBean.getClass().getInterfaces(),
                new OwnerInvocationHandler(personBean));
    }
}
