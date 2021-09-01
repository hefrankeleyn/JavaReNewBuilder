package com.hef.future.demo2;

import java.util.Random;

/**
 * @Date 2021/9/1
 * @Author lifei
 */
public class Shop {

    private static Random random = new Random(23);

    private String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public String getPrice(String productName) {
        double price = calculatePrice(productName);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", shopName, price, code);
    }

    public double calculatePrice(String product) {
        delay();
        return random.nextDouble()*product.charAt(0) + product.charAt(1);
    }

    private void delay() {
        long v = 500 + random.nextInt(2000);
        try {
            Thread.sleep(v);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
