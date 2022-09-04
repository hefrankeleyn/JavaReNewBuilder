package com.hef.review.algorithm.sort;

import static com.google.common.base.Preconditions.*;
import com.google.common.collect.Ordering;

import java.util.Arrays;

/**
 * @Date 2022/9/5
 * @Author lifei
 */
public class ReviewSortMain {

    public static void main(String[] args) {
        Integer[] a = new Integer[]{5, 1, 9, 0, 1, 5, 7, 3, 2};
        ReviewSort<Integer> sort = new ReviewSelectorSort<>();
        System.out.println(Arrays.toString(a));
        sort.sort(a);
        System.out.println(Arrays.toString(a));
        validSorted(a);
    }

    public static void validSorted(Integer[] a) {
        boolean res = Ordering.natural().isOrdered(Arrays.asList(a));
        checkState(res, "顺序不对");
    }
}
