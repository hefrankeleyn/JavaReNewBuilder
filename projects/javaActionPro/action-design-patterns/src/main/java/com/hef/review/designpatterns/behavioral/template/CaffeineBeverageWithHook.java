package com.hef.review.designpatterns.behavioral.template;

/**
 * 定义一个带有钩子的模版方法
 * @Date 2022/10/23
 * @Author lifei
 */
public abstract class CaffeineBeverageWithHook {

    /**
     * 模版方法
     */
    public void prepareRecipe() {
        boilWater(); // 烧水
        brew(); // 冲泡
        pourInCup(); // 倒入杯中
        // 由钩子方法决定是否添加配料
        if (customerWantsCondiments()) {
            addCondiments(); // 添加配料
        }
    }

    protected abstract void brew();

    protected abstract void addCondiments();

    protected void boilWater() {
        System.out.println("烧水.....");
    }

    protected void pourInCup() {
        System.out.println("倒入被中......");
    }

    /**
     * 钩子方法，由子类决定是否覆盖
     * @return
     */
    protected boolean customerWantsCondiments() {
        return true;
    }
}
