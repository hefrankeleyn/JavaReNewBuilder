package com.hef.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Date 2021/8/23
 * @Author lifei
 */
public class FlatMapTest {

    public static void main(String[] args) {
        String[] words = {"hello", "world"};
        List<String> charList = Arrays.stream(words).map(item -> item.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        System.out.println(charList);

        Integer[] a = {1, 2, 3, 4, 5};
        List<Integer> ar = Arrays.stream(a).map(v -> v * v).collect(Collectors.toList());
        System.out.println(ar);

        Integer[][] aa = {{1, 2, 3},{3, 4}};
        List<Integer[]> pairs = Arrays.stream(aa[0])
                .flatMap(i -> Arrays.stream(aa[1])
                        .filter(j->(i+j)%3==0)
                        .map(j -> new Integer[]{i, j})
                ).collect(Collectors.toList());
        pairs.forEach(item-> System.out.print(Arrays.toString(item)+" "));
        System.out.println();

    }
}
