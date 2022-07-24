package com.hef.guava.baseutilities;

import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Date 2022/7/16
 * @Author lifei
 */
public class OrderingMain {

    public static void main(String[] args) {
        OrderingMain orderingMain = new OrderingMain();
//        orderingMain.test01();
        orderingMain.orderApplication();
    }

    private void test01() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(32, 5, 1));
        Integer value = Collections.max(nums, Ordering.usingToString());
        System.out.println(value);
        List<String> names = new ArrayList<>(Arrays.asList("hello", "we", "are", "is", "ar", null));
        Ordering byLenOrdering = new Ordering<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1==null) {
                    return 1;
                }
                if (s2==null) {
                    return -1;
                }
                return Integer.compare(s1.length(), s2.length());
            }
        };
//        System.out.println(names);
////        Collections.sort(names, byLenOrdering.reverse().nullsLast());
//        Collections.sort(names, byLenOrdering);
//        System.out.println(names);
//        Collections.sort(names, byLenOrdering.compound(Ordering.usingToString()));
//        System.out.println(names);
//        Ordering lexicographical = byLenOrdering.lexicographical();
//        List<List<String>> groups = new ArrayList<>();
//        groups.add(new ArrayList<>(Arrays.asList("aa", "ff")));
//        groups.add(new ArrayList<>(Arrays.asList("aa", "bb", "cc")));
//        System.out.println(groups);
//        Collections.sort(groups, Ordering.usingToString().lexicographical());
//        System.out.println(groups);
        System.out.println(names);
        Collections.sort(names, byLenOrdering.onResultOf((v)->v+""));
        System.out.println(names);
//        byLenOrdering.reverse();
    }

    private void orderApplication() {
        List<Integer> result01 = Ordering.natural().greatestOf(new ArrayList<>(Arrays.asList(3, 5, 1)), 2);
        List<Integer> result = Ordering.natural().leastOf(new ArrayList<>(Arrays.asList(3, 5, 1)), 2);
        System.out.println(result01);
        System.out.println(result);
        boolean ordered = Ordering.natural().isStrictlyOrdered(result);
        List<Integer> nums = new ArrayList<>(Arrays.asList(4, 1, 3));
//        List<Integer> resultCopy = Ordering.natural().sortedCopy(nums);
        List<Integer> resultCopy = Ordering.natural().immutableSortedCopy(nums);
        System.out.println(resultCopy);
        System.out.println(nums);
        Integer min = Ordering.natural().min(5, 1);
        Integer max = Ordering.natural().max(5, 1);
        Integer max02 = Ordering.natural().max(Arrays.asList(4, 1));
        System.out.println(min);
        System.out.println(max);
    }
}
