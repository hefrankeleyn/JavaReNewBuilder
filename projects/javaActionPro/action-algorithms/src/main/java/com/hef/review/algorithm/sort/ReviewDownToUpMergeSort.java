package com.hef.review.algorithm.sort;

/**
 * 注意：向上归并排序 和 向下归并排序 的merge算法有细微的差别
 * @Date 2022/9/14
 * @Author lifei
 */
public class ReviewDownToUpMergeSort<T extends Comparable<T>> extends ReviewSort<T>{


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
        int mid = (lo+hi)/2;
        sort(a, lo, mid, aux);
        sort(a, mid+1, hi, aux);
        merge(a, lo, mid, hi, aux);
    }

    private void merge(T[] a, int lo, int mid, int hi, T[] aux) {
        for (int k=lo; k<=hi; k++) {
            aux[k] = a[k];
        }
        for (int k=lo, x=lo, y=mid+1; k<=hi; k++) {
            if (y>hi) a[k] = aux[x++];
            else if (x>mid) a[k] = aux[y++];
            else if (less(aux, x, y)) a[k] = aux[x++];
            else a[k] = aux[y++];
        }
    }
}
