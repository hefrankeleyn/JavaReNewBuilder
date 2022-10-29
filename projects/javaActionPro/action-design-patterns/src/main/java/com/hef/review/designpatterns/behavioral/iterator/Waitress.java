package com.hef.review.designpatterns.behavioral.iterator;

import java.util.Iterator;

/**
 * @Date 2022/10/29
 * @Author lifei
 */
public class Waitress {

//    private DinerMenu dinerMenu;
    private Iterable<MeanItem> dinerMenu;
//    private PancakeHouseMenu pancakeHouseMenu;
    private Iterable<MeanItem> pancakeHouseMenu;
    private Iterable<MeanItem> cafeMenu;

    public Waitress(Iterable<MeanItem> dinerMenu, Iterable<MeanItem> pancakeHouseMenu,
                    Iterable<MeanItem> cafeMenu) {
        this.dinerMenu = dinerMenu;
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.cafeMenu = cafeMenu;
    }

    public void printMenu() {
        // 煎饼屋菜单迭代器
        Iterator<MeanItem> pancakeIterator = pancakeHouseMenu.iterator();
        // 晚餐菜单迭代器
        Iterator<MeanItem> dinerIterator = dinerMenu.iterator();
        // 咖啡菜单
        Iterator<MeanItem> cafeIterator = cafeMenu.iterator();
        System.out.println("煎饼屋菜单......：");
        printMean(pancakeIterator);
        System.out.println("晚餐菜单......：");
        printMean(dinerIterator);
        System.out.println("咖啡菜单......");
        printMean(cafeIterator);
    }

    private void printMean(Iterator iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        // Java5 中包含了 新形式的for语句，称为for/in。
        // 可以让你在一个集合或者一个数组中遍历，而且不需要显式的创建迭代器
        System.out.println("使用Java5的for/in 语法糖，遍历 cafeMean：");
        for (MeanItem menu : cafeMenu) {
            System.out.println(menu);
        }
    }
}
