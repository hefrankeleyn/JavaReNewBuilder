package com.hef.review.designpatterns.creational.builder;

/**
 * @Date 2022/10/11
 * @Author lifei
 */
public class BuilderMain {

    public static void main(String[] args) {
        NutritionFacts nutritionFacts = NutritionFacts.builder().carbohydrate(1).servings(2).servingSize(3)
                .fat(4).calories(5).sodium(6).build();
        ZnBox znBox = new ZnBox.Builder().color("red").size(20)
                .znFlag(true).build();
    }
}
