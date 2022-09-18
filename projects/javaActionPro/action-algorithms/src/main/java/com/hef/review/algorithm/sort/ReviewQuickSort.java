package com.hef.review.algorithm.sort;

import java.util.Random;

/**
 * @Date 2022/9/13
 * @Author lifei
 */
public class ReviewQuickSort<T extends Comparable<T>> extends ReviewSort<T>{


    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) {
            return;
        }
        shuffle(a);

        sort(a, 0, a.length-1);
    }

    private void sort(T[] a, int i, int j) {
        if (i>=j) return;

        int p = partition(a, i, j);
        sort(a, i, p-1);
        sort(a, p+1, j);
    }

    private int partition(T[] a, int low, int high) {
        int j = low, x = low, y = high + 1;
        while (x<y) {
            while (less(a, ++x, j)) if (x==high) break;
            while (less(a, j, --y)) if (y==low) break;
            if (x>=y) break;
            exch(a, x, y);
        }
        exch(a, j, y);
        return y;
    }

    private void shuffle(T[] a) {
        Random random = new Random(23);
        for (int i=0; i<a.length; i++) {
            exch(a, i, i + random.nextInt(a.length - i));
        }
    }
}
