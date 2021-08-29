package com.hef.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * @Date 2021/8/29
 * @Author lifei
 */
public class ForkJoinMain {

    public static void main(String[] args) {
        FunctionPerformanceTest performanceTest = new FunctionPerformanceTest();
        System.out.println("ForkJoin sum done in : "
                + performanceTest.measureSumPerf(ForkJoinMain::forkJoinSum, 10_000_000) + "ms");
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        MyRecursiveSumTask sumTask = new MyRecursiveSumTask(numbers);
        return new ForkJoinPool().invoke(sumTask);
    }
}
