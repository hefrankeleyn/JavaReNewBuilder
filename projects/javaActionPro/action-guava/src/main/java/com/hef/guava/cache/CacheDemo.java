package com.hef.guava.cache;

import com.google.common.base.Strings;
import com.google.common.base.Ticker;
import com.google.common.cache.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Date 2022/7/30
 * @Author lifei
 */
public class CacheDemo {

    public static void main(String[] args) {
//        cacheBaseTimeTest();
        referenceCacheTest();
    }

    private static void referenceCacheTest() {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().weakKeys()
                .recordStats()
                .build();
        CacheStats stats = cache.stats();
        stats.evictionCount();
        Object name = cache.getIfPresent("name");
        System.out.println(name);


    }

    private static void cacheBaseTimeTest() {
        MyTicker myTicker = new MyTicker();
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .ticker(myTicker)
                .expireAfterAccess(3, TimeUnit.SECONDS).build();
        cache.put("name01", "aaa");
        String value = null;
        try {
            value = cache.get("name01", ()->null);
            System.out.println("name01: " + value);
            // 模拟时间流逝
            myTicker.addElapsedTime(TimeUnit.NANOSECONDS.convert(5, TimeUnit.SECONDS));
            value = cache.get("name01", ()->"null");
            System.out.println("name01: " + value);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static class MyTicker extends Ticker {
        private static long start = Ticker.systemTicker().read();
        private long elapsedNano=0l;

        @Override
        public long read() {
            return start+elapsedNano;
        }

        public void addElapsedTime(long elapsedNane) {
            this.elapsedNano += elapsedNane;
        }
    }

    private static void cacheBaseSize() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .weigher((String key, String val)->{
                    return calculateWeight(key, val);
                })
                .maximumWeight(1000)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return generateCacheVal(key);
                    }
                });
        String val = cache.getUnchecked("name");
        try {
            String va = cache.get("name");
            System.out.println(va);

//            cache.get("name", ()->generateVal());
            ConcurrentMap<String, String> map = cache.asMap();
            map.put("name2", "name12");
            System.out.println(cache.get("name2"));


        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static int calculateWeight(String key, String val) {
        return (key+" "+val).length();
    }


    private static String generateVal() {
        return Strings.nullToEmpty(null);
    }

    private static void createCacheDemo() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .removalListener(new MyListener())
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return generateCacheVal(key);
                    }
                });
    }

    public static class MyListener implements RemovalListener<String, String> {

        @Override
        public void onRemoval(RemovalNotification<String, String> notification) {

        }
    }

    private static String generateCacheVal(String key) {
        return key;
    }
}
