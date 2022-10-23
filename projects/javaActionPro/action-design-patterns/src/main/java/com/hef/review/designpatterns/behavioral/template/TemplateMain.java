package com.hef.review.designpatterns.behavioral.template;

/**
 *  模版方法，示例
 * @Date 2022/10/23
 * @Author lifei
 */
public class TemplateMain {

    public static void main(String[] args) {
        CaffeineBeverageWithHook coffee = new CoffeeWithHook();
        coffee.prepareRecipe();
    }
}
