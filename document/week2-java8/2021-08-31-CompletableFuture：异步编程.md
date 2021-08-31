# CompletableFuture 异步编程

[toc]

## 一、Future

Futrue接口是Java5引入。Future 的get方法会阻塞，直至得到一个返回结果。

### 1.1 get方法和join方法

get方法和join方法功效一样，差异在：`future.get()`方法会抛出受检异常，`future.join()` 方不会抛出受检异常；

```
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return createValue();
            }
        });
        doSomthing();
        try {
            // 阻塞，直至得到返回结果
            Double v = future.get();
            System.out.println(v);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
```

## 二、CompletableFuture

CompletableFuture是Future的实现，Java8中引入。

### 2.1 入门示例

#### （1）示例一

这种情况下，发生错误。如果在外面调用`futer.get()` 将一直阻塞

```
public Future<Double> getPriceSync(String product) {
    CompletableFuture<Double> future = new CompletableFuture<>();
    new Thread(()->{
        double res = calculatePrice(product);
        future.complete(res);
    }).start();
    return future;
}
```

#### （2）示例二：异常处理

这种方案下，外面调用`future.get()`时，发生的异常能够捕获到。

```
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
```

#### （3）示例三：工厂类版本

```
    /**
     * 生产者方法，会交给ForkJoinPool池中的某个执行线程运行
     * @param product
     * @return
     */
    public Future<Double> getPriceSync3(String product) {
        return CompletableFuture.supplyAsync(()->calculatePrice(product));
    }
```



### 2.2 ComputableFuture示例，性能比较

#### （1）串行流、并行流、ComputableFuture性能比较

发现 并行流和 computableFuture 性能差不多。

```
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
```

```
        List<Shop> shops = new ArrayList<>(Arrays.asList(new Shop("aa"),
                new Shop("bb"),
                new Shop("cc"),
                new Shop("dd"),
                new Shop("ee"),
                new Shop("ff")));
        long start = System.nanoTime();
        // Done in 4020 ms
        // Done in 6017 ms
        List<String> res = findPrice1(shops);
        // Done in 1012 ms
        // Done in 1013 ms
//        List<String> res = findPrice2(shops);
        // Done in 1011 ms
//        Done in 1013 ms
//        List<String> res = findPrice3(shops);
        System.out.println(res);
        long during = (System.nanoTime()-start)/1_000_000;
        System.out.println("Done in " + during + " ms");
```

#### （2）对ComputableFuture改进。使用并行流，还是ComputableFuture

ComputableFuture 可以调整线程池大小。所以建议：

- 计算密集型操作，没有I/O，推荐使用Stream并行；
- 如果并行的工作单元还涉及到等待I/O的操作，那么使用ComputableFuture 灵活性更好；

```
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
```

