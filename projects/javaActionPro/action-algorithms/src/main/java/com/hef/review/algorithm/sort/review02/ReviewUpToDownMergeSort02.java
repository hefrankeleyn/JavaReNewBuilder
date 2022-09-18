package com.hef.review.algorithm.sort.review02;

import com.hef.review.algorithm.sort.ReviewSort;

/**
 * @Date 2022/9/18
 * @Author lifei
 */
public class ReviewUpToDownMergeSort02<T extends Comparable<T>> extends ReviewSort<T> {
    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) return;
        T[] aux = (T[])new Comparable[a.length];

        for (int k=1; k<=a.length; k = k *2) {
            for (int i=0; i<a.length; i += k*2) {
                merge(a, i, i+k, Math.min(i+k*2-1, a.length-1), aux);
            }
        }
    }

    private void merge(T[] a, int lo, int mid, int hi, T[] aux) {
        for (int k=lo; k<=hi; k++) {
            aux[k] = a[k];
        }
        int x = lo, y=mid;
        for (int k=lo; k<=hi; k++) {
            if (x>=mid) a[k] = aux[y++];
            else if (y>hi) a[k] = aux[x++];
            else if (less(aux, x, y)) a[k] = aux[x++];
            else a[k] = aux[y++];
        }
    }
}
