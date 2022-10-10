package com.hef.review.designpatterns.creational.builder;

/**
 * 营养成分
 * @Date 2022/10/11
 * @Author lifei
 */
public class NutritionFacts {

    private Integer servingSize;
    private Integer servings;
    private Integer calories;
    private Integer fat;
    private Integer sodium;
    private Integer carbohydrate;

    public NutritionFacts(){}
    private NutritionFacts(Builder builder){
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Integer servingSize;
        private Integer servings;
        private Integer calories;
        private Integer fat;
        private Integer sodium;
        private Integer carbohydrate;

        public Builder servingSize(Integer servingSize) {this.servingSize = servingSize; return this;}
        public Builder servings(Integer servings) {this.servings = servings; return this;}
        public Builder calories(Integer calories) {this.calories = calories; return this;}
        public Builder fat(Integer fat) {this.fat = fat; return this;}
        public Builder sodium(Integer sodium) {this.sodium = sodium; return this;}
        public Builder carbohydrate(Integer carbohydrate) {this.carbohydrate = carbohydrate; return this;}
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    public Integer getServingSize() {
        return servingSize;
    }

    public void setServingSize(Integer servingSize) {
        this.servingSize = servingSize;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Integer getSodium() {
        return sodium;
    }

    public void setSodium(Integer sodium) {
        this.sodium = sodium;
    }

    public Integer getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Integer carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    @Override
    public String toString() {
        return "NutritionFacts{" +
                "servingSize=" + servingSize +
                ", servings=" + servings +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", carbohydrate=" + carbohydrate +
                '}';
    }
}
