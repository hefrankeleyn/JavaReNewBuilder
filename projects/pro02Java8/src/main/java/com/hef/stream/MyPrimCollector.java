package com.hef.stream;

import java.util.function.*;
import java.util.stream.Collector;
import java.util.*;


/**
 * 我的质数迭代器
 * @Date 2021/8/29
 * @Author lifei
 */
public class MyPrimCollector implements Collector<Integer,Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
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
        return (map, v)->{
            boolean flag = isPrim(map.get(true), v);
            map.get(flag).add(v);
        };
    }


    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (map1, map2)->{
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
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

    private boolean isPrim(List<Integer> primList, int v) {
        int k = (int)Math.sqrt(v);
        return subPrim(primList, i->i>k).stream().filter(i->i>1).noneMatch(i->v%i==0);
    }

    private <A> List<A> subPrim(List<A> primList, Predicate<A> p) {
        for (int i=0; i<primList.size(); i++) {
            if (p.test(primList.get(i))) {
                return primList.subList(0, i);
            }
        }
        return primList;
    }

    public static void main(String[] args) {
        System.out.println((int)Math.sqrt(2));
    }


}
