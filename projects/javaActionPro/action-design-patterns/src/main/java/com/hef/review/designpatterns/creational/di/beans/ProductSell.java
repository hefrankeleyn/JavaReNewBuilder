package com.hef.review.designpatterns.creational.di.beans;

/**
 * @Date 2022/10/12
 * @Author lifei
 */
public class ProductSell {

    private ProductInfo productInfo;

    public ProductSell(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public void sell() {
        System.out.println("销售：" + productInfo);
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
}
