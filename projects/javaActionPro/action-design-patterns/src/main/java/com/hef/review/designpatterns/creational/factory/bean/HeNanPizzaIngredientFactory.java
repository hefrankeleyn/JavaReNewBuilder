package com.hef.review.designpatterns.creational.factory.bean;

/**
 * 抽象工厂的具体实现
 */
public class HeNanPizzaIngredientFactory implements PizzaIngredientFactory{

    /**
     * 生产 配料01
     * @return
     */
    @Override
    public String createIngredient01() {
        return "HeNan ingredient01";
    }

    /**
     * 生产 配料 02
     * @return
     */
    @Override
    public String createIngredient02() {
        return "HeNan ingredient02";
    }

}
