package com.hef.review.designpatterns.creational.factory;

import com.hef.review.designpatterns.creational.factory.bean.*;

/**
 * 河南独特的Pizza
 * @Date 2022/10/7
 * @Author lifei
 */
public class HeNanPizzaStore02 extends AbstractPizzaStore02 {
    @Override
    AbstractPizza createPizza(String type) {
        PizzaIngredientFactory pizzaIngredientFactory = new HeNanPizzaIngredientFactory();
        AbstractPizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new CheesePizza02(pizzaIngredientFactory);
        }
        return pizza;
    }
}
