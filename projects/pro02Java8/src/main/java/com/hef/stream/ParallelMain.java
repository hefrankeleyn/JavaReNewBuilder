package com.hef.stream;

/**
 * @Date 2021/8/29
 * @Author lifei
 */
public class ParallelMain {

    public static void main(String[] args) {
        int v = Runtime.getRuntime().availableProcessors();
        System.out.println(v);
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "5");
    }
}
