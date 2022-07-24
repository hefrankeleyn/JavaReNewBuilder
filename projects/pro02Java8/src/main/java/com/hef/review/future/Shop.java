package com.hef.review.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @Date 2022/7/5
 * @Author lifei
 */
public class Shop {

    private static Random random = new Random();

    private String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }


    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public double getRate() {
        Shop.delay();
        return Math.random();
    }

    public String getPriceWithCode(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", shopName, price, code);
    }

    public Future<Double> getPriceSync(final String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(()->{
            double result = calculatePrice(product);
            future.complete(result);
        }).start();
        return future;
    }

    public Future<Double> getPriceSyncHandleException(final String product) {
        final CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(()->{
            try {
                double result = calculatePrice(product);
                future.complete(result);
            }catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();
        return future;
    }

    /**
     * 也能管理异常，同时
     * 生产者方法会交由ForkJoinPool 池中的某个执行线程（Executor）运行，但是你也可以使用supplyAsync方法的重载版本，
     * 传递第二个参数指定不同的执行线程执行生产者方法。
     * @param product
     * @return
     */
    public Future<Double> getPriceSyncHandleExceptionOptimize(final String product) {
        return CompletableFuture.supplyAsync(()->calculatePrice(product));
    }



    private double calculatePrice(String product) {
        delay();
        return Math.random() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 模拟延迟
     */
    public static void delay() {
        try {
            int val = random.nextInt(20000);
            Thread.sleep(val);
//            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopName='" + shopName + '\'' +
                '}';
    }
}
