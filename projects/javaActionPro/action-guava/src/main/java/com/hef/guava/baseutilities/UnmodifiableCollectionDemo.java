package com.hef.guava.baseutilities;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

import java.util.Arrays;

/**
 * @Date 2022/7/17
 * @Author lifei
 */
public class UnmodifiableCollectionDemo {

    public static void main(String[] args) {
        ImmutableSet<String> set01 = ImmutableSet.copyOf(Arrays.asList("aa", "bb"));
        System.out.println(set01);
        System.out.println(ImmutableSet.of("aa", "cc", "bb"));
        System.out.println(ImmutableMap.of("name", "world", "where", "beijing"));
        ImmutableSet<String> result = ImmutableSet.<String>builder().add("aaa").add("ccc").build();
        System.out.println(result);
        ImmutableList<String> aa = ImmutableSet.of("aa").asList();
        System.out.println(ImmutableSortedSet.of("a", "c", "b").asList().get(1));
    }
}
