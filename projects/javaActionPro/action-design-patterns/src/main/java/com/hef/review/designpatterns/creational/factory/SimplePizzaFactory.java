package com.hef.review.designpatterns.creational.factory;

import com.hef.review.designpatterns.creational.factory.bean.CheesePizza;
import com.hef.review.designpatterns.creational.factory.bean.Pizza;
import com.hef.review.designpatterns.creational.factory.bean.VeggiePizza;

/**
 * 简单工厂：不是一种设计模式，更像一种编程习惯
 * @Date 2022/10/7
 * @Author lifei
 */
public class SimplePizzaFactory {

    public Pizza createPizza(String type) {
        Pizza pizza;
        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza();
        } else {
            pizza = new Pizza();
        }
        return pizza;
    }
}
