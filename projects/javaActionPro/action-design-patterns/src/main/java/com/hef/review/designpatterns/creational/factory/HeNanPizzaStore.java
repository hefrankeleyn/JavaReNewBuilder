package com.hef.review.designpatterns.creational.factory;

import com.hef.review.designpatterns.creational.factory.bean.*;

/**
 * 河南独特的Pizza
 * @Date 2022/10/7
 * @Author lifei
 */
public class HeNanPizzaStore extends AbstractPizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza;
        if (type.equals("cheese")) {
            pizza = new HeNanCheesePizza();
        } else if (type.equals("veggie")) {
            pizza = new HeNanVeggiePizza();
        } else {
            pizza = new Pizza();
        }
        return pizza;
    }
}
