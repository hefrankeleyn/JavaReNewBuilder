package com.hef.review.designpatterns.behavioral.iterator;

import com.google.common.base.MoreObjects;

/**
 * 菜单项
 * @Date 2022/10/29
 * @Author lifei
 */
public class MeanItem {

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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(MeanItem.class)
                .add("name", name)
                .add("description", description)
                .add("vegetarian", vegetarian)
                .add("price", price)
                .toString();
    }
}
