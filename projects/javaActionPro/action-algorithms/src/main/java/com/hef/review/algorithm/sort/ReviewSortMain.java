package com.hef.review.algorithm.sort;

import static com.google.common.base.Preconditions.*;
import com.google.common.collect.Ordering;
import com.hef.review.algorithm.sort.review02.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Date 2022/9/5
 * @Author lifei
 */
public class ReviewSortMain {

    public static void main(String[] args) {
//        Integer[] a = new Integer[]{5, 1, 9, 0, 1, 5, 7, 3, 2};
//        Integer[] a = new Integer[]{5, 1};
        List<Integer[]> list = Arrays.asList(
                new Integer[]{5, 1, 9, 0, 1, 5, 7, 3, 2},
                new Integer[]{5, 1},
                new Integer[]{4, 3, 9, 8, 1, 5, 6, 3, 2},
                new Integer[]{9,  10, 0, 12, 4, 7, 1, 12, 4, 7, 1, 0, 10, 9,  10, 0, 12, 0, 10, 9,  10, 0, 12, 4, 7, 1, 0, 10}
                );
//        ReviewSort<Integer> sort = new ReviewSelectorSort<>();
//        ReviewSort<Integer> sort = new ReviewHeapSort<>();
        for (Integer[] a : list) {
//            sortTest(new ReviewSelectorSort<>(), a);
//            sortTest(new ReviewHeapSort<>(), a);
//            sortTest(new ReviewMaoPaoSort<>(), a);
//            sortTest(new ReviewQuickSort<>(), a);
//            sortTest(new ReviewThreeWayQuickSort<>(), a);
//            sortTest(new ReviewInsertSort<>(), a);
//            sortTest(new ReviewShellSort<>(), a);
//            sortTest(new ReviewUpToDownMergeSort<>(), a);
//            sortTest(new ReviewDownToUpMergeSort<>(), a);
//            sortTest(new ReviewSelectorSort02<>(), a);
//            sortTest(new ReviewHeapSort02<>(), a);
//            sortTest(new ReviewExchangeSort02<>(), a);
//            sortTest(new ReviewQuickSort02<>(), a);
//            sortTest(new ReviewThreeWayQuickSort02<>(), a);
//            sortTest(new ReviewInsertSort02<>(), a);
//            sortTest(new ReviewShellSort02<>(), a);
//            sortTest(new ReviewDowToUpnMergeSort02<>(), a);
            sortTest(new ReviewUpToDownMergeSort02<>(), a);
        }
    }

    private static void sortTest(ReviewSort<Integer> sort, Integer[] a) {
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
