package com.hef.review.designpatterns.creational.factory;

import com.hef.review.designpatterns.creational.factory.bean.*;

/**
 * 河南独特的Pizza
 * @Date 2022/10/7
 * @Author lifei
 */
public class TianJinPizzaStore extends AbstractPizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza;
        if (type.equals("cheese")) {
            pizza = new TianJinCheesePizza();
        } else if (type.equals("veggie")) {
            pizza = new TianJinVeggiePizza();
        } else {
            pizza = new Pizza();
        }
        return pizza;
    }
}
