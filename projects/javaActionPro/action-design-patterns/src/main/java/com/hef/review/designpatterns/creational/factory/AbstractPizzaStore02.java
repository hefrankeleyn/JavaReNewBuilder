package com.hef.review.designpatterns.creational.factory;

import com.hef.review.designpatterns.creational.factory.bean.AbstractPizza;

/**
 * 工厂方法模式中的 披萨商店
 * @Date 2022/10/7
 * @Author lifei
 */
public abstract class AbstractPizzaStore02 {

    // 个性化的产品由地区决定
    abstract AbstractPizza createPizza(String type);

    public AbstractPizza orderPizza(String type) {
        AbstractPizza pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
