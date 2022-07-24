package com.hef.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Date 2022/7/3
 * @Author lifei
 */
public class MyPrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return ()->new HashMap<Boolean, List<Integer>>(){
            {
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (a, b)->{
            a.get(isPrime(a.get(true), b)).add(b);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (a, b)->{
            a.get(true).addAll(b.get(true));
            a.get(false).addAll(b.get(false));
            return a;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> predicate) {
        int i=0;
        for (A item : list) {
            if (!predicate.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    // 理想情况下，我们会想要一个延迟求值的 takeWhile，这样就可以和noneMatch操作合并
    public static boolean isPrime(List<Integer> list, Integer candidate) {
        int candidateRoot = (int)Math.sqrt(candidate);
        return takeWhile(list, i->i<=candidateRoot).stream().noneMatch(i->candidate%i==0);
    }

    public static boolean isPrimeOld(Integer candidate) {
        int candidateRoot = (int)Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i->candidate%i==0);
    }


    public static void main(String[] args) {
//        Map<Boolean, List<Integer>> result = IntStream.rangeClosed(2, 20).boxed().collect(new MyPrimeNumbersCollector());
//        System.out.println(result);
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long t1 = System.nanoTime();
            int candidate=1_000_000;
            Map<Boolean, List<Integer>> result01 = IntStream.rangeClosed(2, candidate).boxed().collect(new MyPrimeNumbersCollector());
//            Map<Boolean, List<Integer>> result01 = IntStream.rangeClosed(2, candidate).boxed().collect(Collectors.partitioningBy(MyPrimeNumbersCollector::isPrimeOld));
            long t2 = System.nanoTime();
            long duration = (t2-t1)/1_000_000;
            if (duration<fastest) {
                fastest = duration;
            }
        }
        System.out.println("fastest: " + fastest);
    }
}
