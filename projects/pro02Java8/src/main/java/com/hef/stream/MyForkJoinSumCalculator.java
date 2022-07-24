package com.hef.stream;

import java.util.concurrent.RecursiveTask;

/**
 * @Date 2022/7/3
 * @Author lifei
 */
public class MyForkJoinSumCalculator extends RecursiveTask<Long> {

    public final long[] numbers;
    private final int start;
    private final int end;

    private final long THRESHOLD = 10_000;

    public MyForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public MyForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int len = end-start;
        if (len<=THRESHOLD) {
            return computeSequentially();
        }
        MyForkJoinSumCalculator left = new MyForkJoinSumCalculator(numbers, start, start+len/2);
        left.fork();
        MyForkJoinSumCalculator right = new MyForkJoinSumCalculator(numbers, start+len/2+1, end);
        Long rightResult = right.compute();
        Long leftResult = left.join();
        return rightResult+leftResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i=start; i<end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
