package com.hef.review.designpatterns.structural.composite.mean;

/**
 * 示例：组合模式 + 迭代器模式
 * @Date 2022/10/29
 * @Author lifei
 */
public class ApplicationDemo {

    public static void main(String[] args) {
        MenuComponent allMenus = new Menu("所有的菜单", "所有的菜单组合");
        // 一级菜单
        MenuComponent pancakeHouseMenu = new Menu("煎饼屋", "早餐");
        MenuComponent dinerMenu = new Menu("大餐", "晚餐");
        MenuComponent cafeMenu = new Menu("咖啡屋", "饮品");

        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinerMenu);
        allMenus.add(cafeMenu);

        // 二级菜单
        pancakeHouseMenu.add(new MeanItem("Pancake01", "001",  false, 2.99));
        pancakeHouseMenu.add(new MeanItem("Pancake02", "002",  true, 3.99));
        pancakeHouseMenu.add(new MeanItem("Pancake03", "003",  false, 1.99));
        pancakeHouseMenu.add(new MeanItem("Pancake04", "004",  true, 5.99));

        Waitress waitress = new Waitress(allMenus);
        waitress.printMenu();
    }
}
