package com.hef.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @Date 2022/7/3
 * @Author lifei
 */
public class MyToListCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (a, b)->{
            System.out.println("....");
            a.addAll(b);
            return a;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        System.out.println("finisher()");
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        // 定义了IDENTITY_FINISH， 就不会调用finisher方法
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.IDENTITY_FINISH,
                Characteristics.CONCURRENT
                ));
    }

    public static void main(String[] args) {
        String[] a = new String[] {"1", "6", "3", "1", "1", "1"};
//        List<String> result = Arrays.stream(a).collect(new MyToListCollector<String>());
        // 并发
        List<String> result = Arrays.stream(a).parallel().collect(new MyToListCollector<String>());
        System.out.println(result);
    }

}
