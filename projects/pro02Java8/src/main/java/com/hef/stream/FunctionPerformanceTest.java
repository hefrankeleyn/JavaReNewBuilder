package com.hef.stream;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 函数的性能测试
 * @Date 2021/8/29
 * @Author lifei
 */
public class FunctionPerformanceTest {

    public static void main(String[] args) {
        // Sequential sum done in 108
        FunctionPerformanceTest performanceTest = new FunctionPerformanceTest();
        System.out.println("Sequential sum done in " +
                performanceTest.measureSumPerf(performanceTest::sequentialSum, 10_000_000));
        // Parallel sum done in 99
        System.out.println("Parallel sum done in " +
                performanceTest.measureSumPerf(performanceTest::parallelSum, 10_000_000));

        System.out.println("Sequential2 sum done in " +
                performanceTest.measureSumPerf(performanceTest::sequentialSum2, 10_000_000));
        System.out.println("Parallel2 sum done in " +
                performanceTest.measureSumPerf(performanceTest::parallelSum2, 10_000_000));

        System.out.println("Sequential3 sum done in " +
                performanceTest.measureSumPerf(performanceTest::sequentialSum3, 10_000_000));
        System.out.println("Parallel3 sum done in " +
                performanceTest.measureSumPerf(performanceTest::parallelSum3, 10_000_000));


        System.out.println("SideEffect sum done in " +
                performanceTest.measureSumPerf(performanceTest::sideEffectSum, 10_000_000));

        System.out.println("SideEffectParallelSum sum done in " +
                performanceTest.measureSumPerf(performanceTest::sideEffectParallelSum, 10_000_000));

        System.out.println("ForkJoin sum done in : "
                + performanceTest.measureSumPerf(ForkJoinMain::forkJoinSum, 10_000_000));
    }

    public long sequentialSum(long n) {
        return Stream.iterate(1l, i->i+1l).limit(n).reduce(0l, Long::sum);
    }

    public long sequentialSum2(long n) {
        return LongStream.iterate(1l, i->i+1l).limit(n).reduce(0l, Long::sum);
    }

    public long sequentialSum3(long n) {
        return LongStream.rangeClosed(1l, n).limit(n).reduce(0l, Long::sum);
    }

    /**
     * 并行求和
     * @param n
     * @return
     */
    public long parallelSum(long n) {
        return Stream.iterate(1l, i->i+1l).limit(n)
                .parallel()
                .reduce(0l, Long::sum);
    }

    public long parallelSum2(long n) {
        return LongStream.iterate(1l, i->i+1l).limit(n)
                .parallel()
                .reduce(0l, Long::sum);
    }

    public long parallelSum3(long n) {
        return LongStream.rangeClosed(1l, n).limit(n)
                .parallel()
                .reduce(0l, Long::sum);
    }

    public long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1l, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1l, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }


    class Accumulator {
        public long total = 0;
        public void add(long value) {
            total += value;
        }
    }

    /**
     * 测试求和的性能
     * @param adder
     * @param n
     * @return
     */
    public long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i=0; i<10; i++) {
            long start = System.nanoTime();
            Long res = adder.apply(n);
            long during = (System.nanoTime() - start)/1_000_000;
            System.out.println("result: " + res);
            if (fastest>during) fastest = during;

        }
        return fastest;
    }
}
