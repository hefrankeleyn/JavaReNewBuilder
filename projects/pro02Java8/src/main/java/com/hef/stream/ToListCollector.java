package com.hef.stream;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.*;

/**
 * @Date 2021/8/29
 * @Author lifei
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>>{

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("aa", "bb", "cc"));
        ArrayList<String> res = list.stream().collect(ArrayList::new, List::add, List::addAll);
        System.out.println(res);
    }

    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
//        return (list, item)->list.add(item);
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2)->{
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,
                Characteristics.CONCURRENT));
    }
}
