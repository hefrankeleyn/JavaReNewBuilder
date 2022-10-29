package com.hef.review.designpatterns.structural.composite.mean;

/**
 * 服务员
 * @Date 2022/10/29
 * @Author lifei
 */
public class Waitress {
    private MenuComponent menuComponent;

    public Waitress(MenuComponent menuComponent) {
        this.menuComponent = menuComponent;
    }

    public void printMenu() {
        menuComponent.print();
    }
}
