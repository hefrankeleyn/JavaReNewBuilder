package com.hef.review.designpatterns.structural.decorator;

import java.io.*;

/**
 * 装饰器的示例
 * @Date 2022/10/16
 * @Author lifei
 */
public class DecoratorDemo {

    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        // Espresso, 价格为： 0.89
        System.out.println(beverage.getDescription() + ", 价格为： " + beverage.cost());

        // 添加摩卡
        beverage = new Mocha(beverage);
        // Espresso, Mocha, 价格为： 1.09
        System.out.println(beverage.getDescription() + ", 价格为： " + beverage.cost());
        
    }

    private static void inputStreamTest() {
        try {
            InputStream inputStream = new FileInputStream("./DecoratorDemo.java");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            LineNumberInputStream lineNumberInputStream = new LineNumberInputStream(bufferedInputStream);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
