package com.hef.stream;

import com.hef.domain.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Date 2021/8/25
 * @Author lifei
 */
public class CollectorTes {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("aa", "bb", "cc", "dd", "ee"));
        Long count = list.stream().collect(Collectors.counting());
        System.out.println(count);
        long count1 = list.stream().count();
        System.out.println(count1);
        Optional<String> maxOpt = list.stream().collect(Collectors.maxBy(String::compareTo));
        Optional<String> minOpt = list.stream().collect(Collectors.minBy(String::compareTo));
        System.out.println(maxOpt.get());
        System.out.println(minOpt.get());

        Integer[] a = {1, 2, 3, 4, 5, 6};
        Integer sum = Arrays.stream(a).collect(Collectors.summingInt(Integer::intValue));
        System.out.println(sum);
        Double avg = Arrays.stream(a).collect(Collectors.averagingInt(Integer::intValue));
        System.out.println(avg);
        IntSummaryStatistics summaryStatistics = Arrays.stream(a).collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(summaryStatistics);
        String res = list.stream().collect(Collectors.joining());
        System.out.println(res);
        String res2 = list.stream().collect(Collectors.joining(","));
        System.out.println(res2);
        System.out.println("==============================");
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        List<Integer> resList = stream.reduce(new ArrayList<Integer>(),
                (List<Integer> l1, Integer v) -> {
                    l1.add(v);
                    return l1;
                },
                (List<Integer> l2, List<Integer> l) -> {
                    l2.addAll(l);
                    return l2;
                });
        System.out.println(resList);

        Map<Integer, List<Integer>> collect = Arrays.asList(a).stream().collect(Collectors.groupingBy(Integer::intValue));
        System.out.println(collect);
        Map<String, List<Integer>> collect2 = Arrays.asList(a).stream().collect(Collectors.groupingBy(item -> {
            if (item > 3) {
                return "aa";
            } else {
                return "bb";
            }
        }));
        System.out.println(collect2);
        Map<String, Long> collect1 = Arrays.asList(a).stream().collect(Collectors.groupingBy(item -> {
            if (item > 3) {
                return "aa";
            } else {
                return "bb";
            }
        }, Collectors.counting()));
        System.out.println(collect1);

        List<Dish> dishList = new ArrayList<>(Arrays.asList(new Dish("aa", "t01"), new Dish("bb", "t02")));
        Map<String, Dish> map1 = dishList.stream().collect(Collectors.groupingBy(Dish::getType,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Dish::getName)),
                        Optional::get)));
        System.out.println(map1);

        System.out.println(isPrime(49));
        System.out.println(isPrime2(49));

    }

    private static boolean isPrime(int n) {
        return IntStream.range(2, n).noneMatch(v-> n % v==0);
    }

    private static boolean isPrime2(int n) {
        int sqrt = (int)Math.sqrt(n);
        return IntStream.rangeClosed(2, sqrt).noneMatch(v-> n % v==0);
    }
}
