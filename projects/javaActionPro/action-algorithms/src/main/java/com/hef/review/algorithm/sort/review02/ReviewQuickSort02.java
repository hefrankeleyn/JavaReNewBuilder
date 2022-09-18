package com.hef.review.algorithm.sort.review02;

import com.hef.review.algorithm.sort.ReviewSort;
import java.util.*;

/**
 * @Date 2022/9/18
 * @Author lifei
 */
public class ReviewQuickSort02<T extends Comparable<T>> extends ReviewSort<T> {

    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) {
            return;
        }
        shuffle(a);
        sort(a, 0, a.length-1);
    }

    private void shuffle(T[] a) {
        Random random = new Random(23);
        for (int i=0; i<a.length; i++) {
            exch(a, i, i+random.nextInt(a.length-i));
        }
    }

    private void sort(T[] a, int lo, int hi) {
        if (lo>=hi) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private int partition(T[] a, int lo, int hi) {
        int left = lo, right = hi+1, j = lo;
        while (left<right) {
            while (less(a, ++left, j)) if (left==hi) break;
            while (less(a, j, --right)) if (right==lo) break;
            if (left>=right) break;
            exch(a, left, right);
        }
        exch(a, j, right);
        return right;
    }
}
