package com.hef.review.designpatterns.creational.factory;

import com.hef.review.designpatterns.creational.factory.bean.CheesePizza;
import com.hef.review.designpatterns.creational.factory.bean.Pizza;
import com.hef.review.designpatterns.creational.factory.bean.VeggiePizza;

/**
 *  根据type创建不同种类的Pizza
 * @Date 2022/10/7
 * @Author lifei
 */
public class PizzaStore {

    private SimplePizzaFactory pizzaFactory;

    public PizzaStore(SimplePizzaFactory pizzaFactory) {
        this.pizzaFactory = pizzaFactory;
    }

    public Pizza orderPizza(String type) {
        /*Pizza pizza;
        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza();
        } else {
            pizza = new Pizza();
        }*/
        Pizza pizza = pizzaFactory.createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
