package com.hef.review.algorithm.sort.review02;

import com.hef.review.algorithm.sort.ReviewSort;

import java.util.Random;

/**
 * @Date 2022/9/18
 * @Author lifei
 */
public class ReviewThreeWayQuickSort02<T extends Comparable<T>> extends ReviewSort<T> {
    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) {
            return;
        }
        shuffle(a);
        sort(a, 0, a.length-1);
    }

    private void sort(T[] a, int lo, int hi) {
        if (lo>=hi) return;

        int lt = lo, left = lo+1, right = hi;
        while (left<=right) {
            int com = a[left].compareTo(a[lt]);
            if (com==0) left++;
            else if (com>0) exch(a, left, right--);
            else exch(a, left++, lt++);
        }
        sort(a, lo, lt-1);
        sort(a, left, hi);
    }

    private void shuffle(T[] a) {
        Random random = new Random(23);
        for (int i=0; i<a.length; i++) {
            exch(a, i, i+random.nextInt(a.length-i));
        }
    }
}
