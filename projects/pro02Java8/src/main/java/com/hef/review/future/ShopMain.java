package com.hef.review.future;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Date 2022/7/5
 * @Author lifei
 */
public class ShopMain {

    private List<Shop> shops = new ArrayList<>();


    public ShopMain(List<Shop> shops) {
        this.shops = shops;
    }

    public static void main(String[] args) {
//        compareAsyncAndSync();

        // 优化并行
//        optimizeAsync();

//        optimizeAsyncWithDiscount();
    }

    private static void collectorTest() {
        CompletableFuture f = null;
        List<Shop> shopList = Arrays.asList(
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("HelloShop"),
                new Shop("WorldShop"),
                new Shop("AaShop"),
                new Shop("BbShop"),
                new Shop("CcShop"),
                new Shop("BuyItAll")
        );
        Map<String, Optional<Shop>> collect = shopList
                .stream().collect(Collectors.groupingBy(Shop::getShopName, Collectors.maxBy(Comparator.comparingInt(item -> item.getShopName().length()))));
    }


    private static void optimizeAsync() {
        List<Shop> shopList = Arrays.asList(
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("HelloShop"),
                new Shop("WorldShop"),
                new Shop("AaShop"),
                new Shop("BbShop"),
                new Shop("CcShop"),
                new Shop("BuyItAll")
        );
        ShopMain shopMain = new ShopMain(shopList);
        /**
         * 版本一：顺序执行输出的结果：
         * [ BestPrice price is 122.60,  LetsSaveBig price is 144.49,  MyFavoriteShop price is 178.24,  HelloShop price is 165.89,  WorldShop price is 138.96,  AaShop price is 219.77,  BbShop price is 129.27,  CcShop price is 150.43,  BuyItAll price is 176.73]
         * Done in 9101 msecs.
         */
        shopMain.findPricePerformance("myPhone27S", shopMain::finPrices);

        /**
         * 版本二： 并行流的性能：
         * [ BestPrice price is 132.98,  LetsSaveBig price is 144.35,  MyFavoriteShop price is 159.89,  HelloShop price is 208.40,  WorldShop price is 163.43,  AaShop price is 177.39,  BbShop price is 227.55,  CcShop price is 134.98,  BuyItAll price is 215.16]
         * Done in 2022 msecs.
         */
        shopMain.findPricePerformance("myPhone27S", shopMain::finPricesParallel);

        /**
         * 版本三： future 的并行
         *  [ BestPrice price is 130.03,  LetsSaveBig price is 158.38,  MyFavoriteShop price is 146.00,  HelloShop price is 229.29,  WorldShop price is 188.18,  AaShop price is 132.24,  BbShop price is 142.20,  CcShop price is 181.17,  BuyItAll price is 173.35]
         * Done in 2017 msecs.
         */
        shopMain.findPricePerformance("myPhone27S", shopMain::findPricesFutureSupportAsync);

        /**
         * 版本四：使用指定的线程池
         *  [ BestPrice price is 142.33,  LetsSaveBig price is 125.06,  MyFavoriteShop price is 207.20,  HelloShop price is 189.72,  WorldShop price is 175.59,  AaShop price is 190.80,  BbShop price is 129.52,  CcShop price is 224.05,  BuyItAll price is 127.98]
         * Done in 1017 msecs.
         */
        shopMain.findPricePerformance("myPhone27S", shopMain::findPricesFutureSupportAsyncWithExecutor);
    }

    private static void optimizeAsyncWithDiscount() {
        List<Shop> shopList = Arrays.asList(
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("HelloShop"),
                new Shop("WorldShop"),
//                new Shop("AaShop"),
//                new Shop("BbShop"),
//                new Shop("CcShop"),
                new Shop("BuyItAll")
        );
        ShopMain shopMain = new ShopMain(shopList);

        /**
         * 串行运行
         * [BestPrice price is 176.84, LetsSaveBig price is 223.72, MyFavoriteShop price is 124.12, HelloShop price is 133.0, WorldShop price is 189.52, BuyItAll price is 161.17]
         * Done in 12078 msecs.
         */
//        shopMain.findPricePerformance("myPhone27S", shopMain::finPricesWithDiscount);
        /**
         * 并行执行
         * [BestPrice price is 121.54, LetsSaveBig price is 171.07, MyFavoriteShop price is 144.75, HelloShop price is 130.28, WorldShop price is 196.79, BuyItAll price is 126.77]
         * Done in 2014 msecs.
         */
//        shopMain.findPricePerformance("myPhone27S", shopMain::finPricesWithDiscountFutureAsync);
        shopMain.anyOfFuture("myPhone27S");
    }


    // 版本一：串行
    public List<String> finPrices(String product) {
        return  shops.stream()
                .map(shop->String.format(" %s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    // 版本二：并行
    public List<String> finPricesParallel(String product) {
        return  shops.parallelStream()
                .map(shop->String.format(" %s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    // 版本三：使用CompletableFuture.supplyAsync 实现并行
    public List<String> findPricesFutureSupportAsync(String product) {
        // stream的延迟执行，会把并行变成串行（因为join时阻塞的）
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format(" %s price is %.2f", shop.getShopName(), shop.getPrice(product))))
                .collect(Collectors.toList());
        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }


    /**
     * 版本四：指定线程池
     * @param product
     * @return
     */
    public List<String> findPricesFutureSupportAsyncWithExecutor(String product) {
        // 通过 N(threads) = N(CPU) * U(CPU) * (1 + W/C)  的公式，可以估算出，8核下，建议创建的最大线程数为 8 * 1 * （1+100) 约等于800
        // 创建的是一个由守护线程构成的线程池。这种方式不会组织程序关停
        final ExecutorService executorService = Executors.newFixedThreadPool(Math.min(shops.size(), 200), (runnable) -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
        // stream的延迟执行，会把并行变成串行（因为join时阻塞的）
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format(" %s price is %.2f", shop.getShopName(), shop.getPrice(product)), executorService))
                .collect(Collectors.toList());
        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }


    public List<String> finPricesWithDiscount(String product) {
        return  shops.stream()
                .map(shop->shop.getPriceWithCode(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    /**
     * 并行执行
     * @param product
     * @return
     */
    public List<String> finPricesWithDiscountFutureAsync(String product) {
        ExecutorService executorService = Executors.newFixedThreadPool(Math.min(shops.size(), 200), (r) -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceWithCode(product), executorService))
                // 解析操作， 不耗时，直接同步操作
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executorService)))
                .collect(Collectors.toList());
        // 等待流中所有的Future执行完，并取出返回值
        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public void mergeCompletableFuture(String product) {
        List<CompletableFuture<Double>> result = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                .thenCombine(CompletableFuture.supplyAsync(ShopMain::getRate), (p, r) -> p * r))
                .collect(Collectors.toList());

    }

    public void anyOfFuture(String product) {
        CompletableFuture[] completableFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product)))
                .map(f -> f.thenAccept(System.out::println))
                .toArray(x -> new CompletableFuture[x]);
        CompletableFuture.anyOf(completableFutures).join();
    }



    public static double getRate() {
        Shop.delay();
        return Math.random();
    }

    // 测试性能
    public void findPricePerformance(String product, Function<String, List<String>> function) {
        long t1 = System.nanoTime();
        System.out.println(function.apply(product));
        long duration = (System.nanoTime() - t1)/1_000_000;
        System.out.println("Done in " + duration + " msecs.");
    }

    /**
     * 对比同步和异步
     * 同步：
     * Invocation returned after 1006 msecs
     * Price is 178.47
     * Price returned after 4045 msecs
     *
     * 异步：
     * Invocation returned after 68 msecs
     * Price is 193.05
     * Price returned after 3094 msecs
     */
    private static void compareAsyncAndSync() {
        Shop shop = new Shop("BestShop");
        long t1 = System.nanoTime();
//        double price = shop.getPrice("my favorite product");
//        Future<Double> futurePrice = shop.getPriceSync("my favorite product");
        Future<Double> futurePrice = shop.getPriceSyncHandleExceptionOptimize("my favorite product");
        long invocationTime = (System.nanoTime() - t1)/1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        doSomeingElse();

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = (System.nanoTime() - t1)/1_000_000;
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private static void doSomeingElse() {
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
