package com.hef.review.designpatterns.structural.composite.mean;

import com.google.common.base.MoreObjects;

/**
 * 菜单项
 * @Date 2022/10/29
 * @Author lifei
 */
public class MeanItem extends MenuComponent{

    private final String name;
    private final String description;
    private final boolean vegetarian;
    private final double price;

    public MeanItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void print() {
        String res = MoreObjects.toStringHelper(MeanItem.class)
                .add("name", name)
                .add("description", description)
                .add("vegetarian", vegetarian)
                .add("price", price)
                .toString();
        System.out.println(res);
    }
}
