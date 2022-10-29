package com.hef.review.designpatterns.behavioral.iterator;

/**
 * 迭代器模式
 * @Date 2022/10/29
 * @Author lifei
 */
public class ApplicationDemo {

    public static void main(String[] args) {
        Iterable<MeanItem> dinerMenu = new DinerMenu();
        Iterable<MeanItem> pancakeHouseMenu = new PancakeHouseMenu();
        Iterable<MeanItem> cafeMean = new CafeMenu();
        Waitress waitress = new Waitress(dinerMenu, pancakeHouseMenu, cafeMean);
        waitress.printMenu();
    }
}
