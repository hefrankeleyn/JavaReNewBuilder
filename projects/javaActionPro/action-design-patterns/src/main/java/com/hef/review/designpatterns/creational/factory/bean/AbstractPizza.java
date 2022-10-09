package com.hef.review.designpatterns.creational.factory.bean;

/**
 * 抽象工厂的 使用
 * @Date 2022/10/7
 * @Author lifei
 */
public abstract class AbstractPizza {

    // 配料 01
    protected String ingredient01;
    // 配料 02
    protected String ingredient02;

    // 配料工厂
    protected PizzaIngredientFactory pizzaIngredientFactory;

    public AbstractPizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    /**
     * 准备配置：根据需要使用 ingredient01 和 ingredient02
     */
    public abstract void prepare();

    public void bake() {
        System.out.println("bake...");
    }

    public void cut() {
        System.out.println("cut...");
    }

    public void box() {
        System.out.println("box....");
    }
}
