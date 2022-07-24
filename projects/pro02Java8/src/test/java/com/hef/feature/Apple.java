package com.hef.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

/**
 * @Date 2022/6/27
 * @Author lifei
 */
public class Apple {

    private int weight;
    private String country;

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight()>150;
    }


    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        List<Apple> apples01 = filterApples(inventory, Apple::isHeavyApple);
        List<Apple> apples02 = filterApples(inventory, (Apple apple) -> apple.getWeight() > 150);
    }
}
