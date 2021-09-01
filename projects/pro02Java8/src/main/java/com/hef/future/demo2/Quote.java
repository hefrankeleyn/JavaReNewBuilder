package com.hef.future.demo2;

/**
 * @Date 2021/9/1
 * @Author lifei
 */
public class Quote {

    private String shopName;
    private double price;
    private Discount.Code code;

    public Quote(String shopName, double price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.code = code;
    }

    public static Quote parse(String str) {
        String[] split = str.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code code = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, code);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getCode() {
        return code;
    }
}
