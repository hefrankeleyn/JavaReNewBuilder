package com.hef.future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Date 2021/8/31
 * @Author lifei
 */
public class FutureMain {

    public static void main(String[] args) {
        testFuture1();

        // findPrice 测试
        System.out.println("findPrice 测试");
        List<Shop> shops = new ArrayList<>(Arrays.asList(
                new Shop("aa"),
                new Shop("bb"),
                new Shop("cc"),
                new Shop("dd"),
                new Shop("ee"),
                new Shop("ff"),
                new Shop("gg"),
                new Shop("hh"),
                new Shop("ii"),
                new Shop("ff2"),
                new Shop("gg2"),
                new Shop("hh2"),
                new Shop("ii2")
        ));
        long start = System.nanoTime();
        // Done in 4020 ms
        // Done in 6017 ms
//        List<String> res = findPrice1(shops);
        // Done in 1012 ms
        // Done in 1013 ms
        // Done in 2017 ms
//        List<String> res = findPrice2(shops);
        // Done in 1011 ms
//        Done in 1013 ms
        // Done in 2017 ms
        // Done in 2015 ms
//        List<String> res = findPrice3(shops);
        // Done in 1017 ms
        List<String> res = findPrice4(shops);
        System.out.println(res);
        long during = (System.nanoTime()-start)/1_000_000;
        System.out.println("Done in " + during + " ms");

    }

    private static List<String> findPrice1(List<Shop> shops) {
        return shops.stream().map(shop -> shop.getShopName() + " price is " + shop.getPrice(shop.getShopName()))
                .collect(Collectors.toList());
    }

    private static List<String> findPrice2(List<Shop> shops) {
        return shops.stream().parallel().map(shop -> shop.getShopName() + " price is " + shop.getPrice(shop.getShopName()))
                .collect(Collectors.toList());
    }

    private static List<String> findPrice3(List<Shop> shops) {
        List<CompletableFuture<String>> futureList = shops.stream().map(shop -> CompletableFuture.supplyAsync(
                () -> shop.getShopName() + " price is " + shop.getPrice(shop.getShopName())))
                .collect(Collectors.toList());
        List<String> res = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        return res;
    }

    private static List<String> findPrice4(List<Shop> shops) {
        // 创建一个守护线程的线程池
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                // 守护线程不会阻止程序的关闭
                thread.setDaemon(true);
                return thread;
            }
        });
        // Java 程序无法终止或退出一个正在运行的线程，所以最后剩下的线程会由于一直等待无法发生的事情而发生问题。
        // 因此，设为守护线程，程序退出，线程就会被回收
        List<CompletableFuture<String>> futureList = shops.stream().map(shop -> CompletableFuture.supplyAsync(
                () -> shop.getShopName() + " price is " + shop.getPrice(shop.getShopName()), executor))
                .collect(Collectors.toList());
        List<String> res = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        return res;
    }


    private static void testFuture1() {
        Shop shop = new Shop();
        long start = System.nanoTime();
        Future<Double> future = shop.getPriceSync("hello");
        long during1 = (System.nanoTime() - start)/1_000_000;
        System.out.println("Invocation returned after " + during1 + " ms");
        doSomethingElse();
        try {
            Double res = future.get();
            System.out.printf("price is %.2f%n", res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long during2 = (System.nanoTime() - start)/1_000_000;
        System.out.println("Price returned after " + during2 + " ms");
    }

    private static void doSomethingElse() {
        System.out.println("doSomething else...");
    }


}
