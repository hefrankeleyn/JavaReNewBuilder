package com.hef.review.designpatterns.creational.factory.bean;

/**
 * 具体的Pizza 使用抽象工厂
 * @Date 2022/10/7
 * @Author lifei
 */
public class CheesePizza02 extends AbstractPizza{
    // 传递一个具体的配料工厂
    public CheesePizza02(PizzaIngredientFactory pizzaIngredientFactory) {
        super(pizzaIngredientFactory);
    }

    // 只使用配料01
    @Override
    public void prepare() {
        this.ingredient01 = pizzaIngredientFactory.createIngredient01();
    }
}
