package com.hef.stream;

import java.util.*;
/**
 * @Date 2021/8/24
 * @Author lifei
 */
public class ReduceTest {

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 8));
        Integer res = a.stream().reduce(0, (i, j) -> i + j);
        System.out.println(res);
        Integer res1 = a.stream().reduce(0, Integer::sum);
        System.out.println(res1);
        Optional<Integer> res2 = a.stream().reduce(Integer::sum);
        System.out.println(res2.orElse(0));
        Optional<Integer> maxOptional = a.stream().reduce(Integer::max);
        System.out.println(maxOptional.orElse(0));
        Optional<Integer> minOptional = a.stream().reduce(Integer::min);
        System.out.println(minOptional.orElse(0));
        long count = a.stream().count();
        System.out.println(count);

    }
}
