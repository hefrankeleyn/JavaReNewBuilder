package com.hef.future.demo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Date 2021/9/1
 * @Author lifei
 */
public class FutureMain2 {

    public static void main(String[] args) {
        List<Shop> shopList = new ArrayList<>(Arrays.asList(
                new Shop("aa"),
                new Shop("bb"),
                new Shop("cc"),
                new Shop("dd"),
                new Shop("ee"),
                new Shop("ff"),
                new Shop("gg"),
                new Shop("hh"),
                new Shop("ii"),
                new Shop("jj"),
                new Shop("kk"),
                new Shop("ll"),
                new Shop("mm")
        ));

        long start = System.nanoTime();
        // Finish : 26184 ms.
//        List<String> res = findPrice(shopList, "apple");

        // Finish : 2135 ms.
//        List<String> res = findPrice2(shopList, "apple");

        // Finish : 4126 ms.
//        List<Double> res = findRateResult(shopList, "apple");
        // Finish : 4102 ms.Î
        /*List<Double> res = findRateResult2(shopList, "apple");
        System.out.println(res);*/
        thenAccept(shopList, "apple");
        long during = (System.nanoTime()-start)/1_000_000;
        System.out.println("Finish : " + during + " ms. ");
    }

    /**
     * 顺序流
     * @param shopList
     * @param product
     * @return
     */
    private static List<String> findPrice(List<Shop> shopList, String product) {
        return shopList.stream().map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    private static List<String> findPrice2(List<Shop> shopList, String product) {
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(shopList.size(), 100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        List<CompletableFuture<String>> futureList = shopList.stream()
                // 以异步的方式获取每个shop中指定产品的原始价格
                .map(stop -> CompletableFuture.supplyAsync(() -> stop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                // thenCompose 将两个 异步操作进行流水线
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor))
                ).collect(Collectors.toList());
        return futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private static List<Double> findRateResult(List<Shop> shopList, String productName) {
        List<CompletableFuture<Double>> futureList = shopList.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.calculatePrice(productName))
                .thenCombine(CompletableFuture.supplyAsync(() -> getRate()), (price, rate) -> price * rate)).collect(Collectors.toList());
        return futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private static List<Double> findRateResult2(List<Shop> shopList, String productName) {
        List<CompletableFuture<Double>> futureList = shopList.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.calculatePrice(productName)))
                .map(future->future.thenCombine(CompletableFuture.supplyAsync(()->getRate()), (price, rate)->price*rate))
                .collect(Collectors.toList());
        return futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private static double getRate() {
        delay();
        return 23.3;
    }

    private static void delay() {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void thenAccept(List<Shop> shopList, String productName) {
        Stream<CompletableFuture<String>> futureStream = shopList.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(productName)))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote))));
        CompletableFuture[] completableFutures = futureStream.map(future -> future.thenAccept(System.out::println))
                .toArray(size -> new CompletableFuture[size]);
//        CompletableFuture.allOf(completableFutures).join();
        CompletableFuture.anyOf(completableFutures).join();
    }
}
