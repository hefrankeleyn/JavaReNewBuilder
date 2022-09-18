package com.hef.review.algorithm.sort.review02;

import com.hef.review.algorithm.sort.ReviewSort;

/**
 * @Date 2022/9/14
 * @Author lifei
 */
public class ReviewHeapSort02<T extends Comparable<T>> extends ReviewSort<T> {

    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) {
            return;
        }
        int k = a.length/2, n = a.length;
        while (k>=1) sink(a, k--, n);

        while (n>1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }
    }

    private void sink(T[] a, int k, int n) {
        while (2*k<=n) {
            int j = 2 * k;
            if (j+1<=n && less(a, j, j+1)) j++;
            if (less(a, j, k)) break;
            exch(a, j, k);
            k = j;
        }
    }

    public boolean less(T[] a, int i, int j) {
        return a[i-1].compareTo(a[j-1])<0;
    }

    public void exch(T[] a, int i, int j) {
        T t = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = t;
    }
}
