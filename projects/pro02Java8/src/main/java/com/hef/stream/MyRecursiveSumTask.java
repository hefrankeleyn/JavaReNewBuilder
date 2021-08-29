package com.hef.stream;

import java.util.concurrent.RecursiveTask;

/**
 * 求和
 * @Date 2021/8/29
 * @Author lifei
 */
public class MyRecursiveSumTask extends RecursiveTask<Long> {

    private int start;
    private int end;
    private long[] numbers;

    private static final int THRESHOLD = 10_000;

    public MyRecursiveSumTask(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public MyRecursiveSumTask(long[] numbers, int start, int end) {
        this.start = start;
        this.end = end;
        this.numbers = numbers;
    }

    @Override
    protected Long compute() {
        int len = end-start;
        if (len<=THRESHOLD) {
            return computeSequentially();
        }
        MyRecursiveSumTask leftTask = new MyRecursiveSumTask(numbers, start, start+len/2);
        // 利用另一个Fork， 异步执行新创建的子任务
        leftTask.fork();
        MyRecursiveSumTask rightTask = new MyRecursiveSumTask(numbers, start+len/2, end);
        // 同步执行第二个子任务
        Long rightRes = rightTask.compute();
        // 读取第一个任务的结果，如果尚未完成就等待
        Long leftRes = leftTask.join();
        // 合并两个任务
        return leftRes + rightRes;
    }

    /**
     * 子任务不可再分时 顺序执行
     * @return
     */
    private long computeSequentially() {
        long sum = 0;
        for (int i=start; i<end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
