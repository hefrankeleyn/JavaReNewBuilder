package com.hef.feature;

import com.hef.stream.MyForkJoinSumCalculator;
import org.junit.Test;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.stream.*;

/**
 * @Date 2022/7/2
 * @Author lifei
 */
public class ReduceTest {

    @Test
    public void reduce01Test() {

        List<Integer> numbers = new ArrayList<>(Arrays.asList(4,2,1,0));
        numbers.stream().mapToDouble(Double::valueOf);
        IntStream intStream = numbers.stream().mapToInt(Integer::valueOf);

        Integer result = numbers.stream().reduce(1, (Integer a, Integer b)->a+b);
        System.out.println(result);
        List<String> worlds = new ArrayList<>(Arrays.asList("hello", "world"));
        Integer result2 = worlds.stream().reduce(0, (Integer a, String b) -> a + b.length(), (Integer a2, Integer b2) -> a2 + b2);


    }

    @Test
    public void fileStreamTest() {
        String filePath = "/Users/lifei/Documents/workspace/githubRepositoies/JAVARebuild/README.md";
        try (Stream<String> fileStream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            fileStream.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Collection<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

    }

    @Test
    public void streamTest01() {
        Stream.iterate(new int[]{0, 1}, (a)->new int[]{a[1], a[0]+a[1]})
                .limit(10)
                .map(a->a[0]+", ")
                .forEach(System.out::print);
        System.out.println();
    }

    @Test
    public void streamGenerate() {
//        Stream.generate(Math::random).limit(5).forEach(System.out::println);
        IntSupplier intSupplier = new IntSupplier() {
            private int pre = 0;
            private int next = 1;
            @Override
            public int getAsInt() {
                int current = pre;
                pre = next;
                next = current+next;
                return current;
            }
        };
        IntStream.generate(intSupplier).limit(10).forEach(i->System.out.print(i+", "));
        System.out.println();
    }

    @Test
    public void collectorTest() {
        List<Integer> list = new ArrayList<>(Arrays.asList(4,2,1,0));
        list.stream().collect(Collectors.toList());
        list.stream().collect(Collectors.counting());
        list.stream().count();
        list.stream().collect(Collectors.maxBy(Integer::compare));
        list.stream().collect(Collectors.minBy(Integer::compare));
        list.stream().collect(Collectors.summingInt(Integer::intValue));
        list.stream().collect(Collectors.averagingInt(Integer::intValue));
        IntSummaryStatistics result = list.stream().collect(Collectors.summarizingInt(Integer::intValue));
//        System.out.println(result);
        String res = list.stream().map(a -> a + "").collect(Collectors.joining(","));
        List<String> numList = new ArrayList<>(Arrays.asList("4", "5", "1"));
        List<Integer> reduce = numList.stream().map(Integer::valueOf).map(Arrays::asList).reduce(new ArrayList<Integer>(), (List<Integer> a1, List<Integer> a2) -> {
            a1.addAll(a2);
            return a1;
        });
        System.out.println(reduce);
        List<Integer> reduce1 = numList.stream().map(Integer::valueOf).reduce(new ArrayList<Integer>(), (List<Integer> a, Integer b) -> {
            a.add(b);
            return a;
        }, (List<Integer> a1, List<Integer> a2) -> {
            a1.addAll(a2);
            return a1;
        });
        System.out.println(reduce1);
    }

    @Test
    public void collectorInterfaceTest() {
        double sqrt = Math.sqrt(4);
        System.out.println(sqrt);
    }

    @Test
    public void sumTest01() {
        System.out.println(Runtime.getRuntime().availableProcessors());
        Function<Long, Long> parallelFunc = (a)->Stream.iterate(1l, v->v+1)
                .limit(a).parallel().reduce(0l, Long::sum);
        Function<Long, Long> sequentialFunc = (a)->Stream.iterate(1l, v->v+1)
                .limit(a).reduce(0l, Long::sum);
        // SequentialStream sum done : 114 msecs.
        System.out.println("SequentialStream sum done : "+performanceTest(sequentialFunc, 10_000_000l)+" msecs.");
        // ParallelStream sum done : 134 msecs.
        System.out.println("ParallelStream sum done : "+performanceTest(parallelFunc, 10_000_000l)+" msecs.");

        // 使用数字流
        Function<Long, Long> numParallelFunc= (a)-> LongStream.rangeClosed(1l, a).parallel().reduce(0l, Long::sum);
        // ParallelStream range sum done : 1 msecs.
        System.out.println("ParallelStream range sum done : "+performanceTest(numParallelFunc, 10_000_000l)+" msecs.");
    }

    private long performanceTest(Function<Long, Long> function, Long candidate) {
        long fastVal = Long.MAX_VALUE;
        for (int i=0; i<10; i++) {
            long t1 = System.nanoTime();
            Long result = function.apply(candidate);
            long duration = (System.nanoTime() - t1)/1_000_000;
            System.out.println("result: " + result);
            if (duration<fastVal) fastVal = duration;
        }
        return fastVal;
    }

    @Test
    public void forkJoinTest() {
        System.out.println("ForkJoin summary: "+performanceTest(ReduceTest::forkJoinSummary, 10_000_000l) + " ms.");
    }

    public static long forkJoinSummary(long n) {
        long[] numbers = LongStream.iterate(0, i -> i + 1).limit(n).toArray();
        MyForkJoinSumCalculator calculator = new MyForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(calculator);
    }
}
