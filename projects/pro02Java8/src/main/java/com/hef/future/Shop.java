package com.hef.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @Date 2021/8/31
 * @Author lifei
 */
public class Shop {

    private static Random random = new Random(23);

    private String shopName;

    public Shop() {
    }

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public static String connectNameAndPrice(Shop shop) {
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", shop.getShopName(), shop.getPrice(shop.getShopName()), shop.getShopName(), code);
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceSync(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(()->{
            double res = calculatePrice(product);
            future.complete(res);
        }).start();
        return future;
    }

    public Future<Double> getPriceSync2(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(()->{
            try {
                double res = calculatePrice(product);
                future.complete(res);
            }catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();
        return future;
    }

    /**
     * 生产者方法，会交给ForkJoinPool池中的某个执行线程运行
     * @param product
     * @return
     */
    public Future<Double> getPriceSync3(String product) {
        return CompletableFuture.supplyAsync(()->calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private void delay() {
        try {
            Thread.sleep(1000l);
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
}
