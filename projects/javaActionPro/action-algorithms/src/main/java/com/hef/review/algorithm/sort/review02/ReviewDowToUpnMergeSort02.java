package com.hef.review.algorithm.sort.review02;

import com.hef.review.algorithm.sort.ReviewSort;

/**
 * 因为要使用 额外的空间， 注意，比较的时候，使用额外空间的位置
 * @Date 2022/9/18
 * @Author lifei
 */
public class ReviewDowToUpnMergeSort02<T extends Comparable<T>> extends ReviewSort<T> {
    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) {
            return;
        }
        T[] aux = (T[])new Comparable[a.length];
        sort(a, 0, a.length-1, aux);
    }

    private void sort(T[] a, int lo, int hi, T[] aux) {
        if (lo>=hi) return;
        int mid = (lo + hi)/2;
        sort(a, lo, mid, aux);
        sort(a, mid+1, hi, aux);
        merge(a, lo, mid, hi, aux);
    }
    private void merge(T[] a, int lo, int mid, int hi, T[] aux) {
        for (int k=lo; k<=hi; k++) {
            aux[k] = a[k];
        }
        int x = lo, y = mid+1;
        for (int k=lo; k<=hi; k++) {
            if (x>mid) a[k] = aux[y++];
            else if (y>hi) a[k] = aux[x++];
            else if (less(aux, x, y)) a[k] = aux[x++];
            else a[k] = aux[y++];
        }
    }
}
