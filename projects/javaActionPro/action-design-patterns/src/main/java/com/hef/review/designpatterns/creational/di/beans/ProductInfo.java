package com.hef.review.designpatterns.creational.di.beans;

/**
 * @Date 2022/10/12
 * @Author lifei
 */
public class ProductInfo {
    private String productName;
    private int productVersion;

    public ProductInfo(String productName, int productVersion) {
        this.productName = productName;
        this.productVersion = productVersion;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(int productVersion) {
        this.productVersion = productVersion;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productName='" + productName + '\'' +
                ", productVersion='" + productVersion + '\'' +
                '}';
    }
}
