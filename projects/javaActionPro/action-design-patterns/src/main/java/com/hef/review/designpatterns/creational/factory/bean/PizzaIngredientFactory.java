package com.hef.review.designpatterns.creational.factory.bean;

/**
 * 抽象工厂 中的接口类
 */
public interface PizzaIngredientFactory {

    /**
     * 生产 配料01
     * @return
     */
    String createIngredient01();

    /**
     * 生产 配料 02
     * @return
     */
    String createIngredient02();

}
