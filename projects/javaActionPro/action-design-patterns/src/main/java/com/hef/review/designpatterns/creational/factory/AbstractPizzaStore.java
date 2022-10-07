package com.hef.review.designpatterns.creational.factory;

import com.hef.review.designpatterns.creational.factory.bean.Pizza;

/**
 * @Date 2022/10/7
 * @Author lifei
 */
public abstract class AbstractPizzaStore {

    // 个性化的产品由地区决定
    abstract Pizza createPizza(String type);

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
