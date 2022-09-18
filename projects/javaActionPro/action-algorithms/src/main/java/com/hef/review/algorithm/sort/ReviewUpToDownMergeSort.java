package com.hef.review.algorithm.sort;

/**
 * 归并排序：容易错误的是merge， 比较的时候要用 副本aux 的元素进行比较
 * @Date 2022/9/13
 * @Author lifei
 */
public class ReviewUpToDownMergeSort<T extends Comparable<T>> extends ReviewSort<T> {

    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) {
            return;
        }
        T[] aux = (T[])new Comparable[a.length];
        for (int k=1; k<=a.length; k = k * 2) {
            for (int i=0; i<a.length; i+=k*2) {
                merge(a, i, i+k, Math.min(i+2*k-1, a.length-1), aux);
            }
        }
    }

    private void merge(T[] a, int low, int mid, int high, T[] aux) {
        for (int k=low; k<=high; k++) {
            aux[k] = a[k];
        }
        int k = low, x = low, y = mid;
        while (k<=high) {
            if (x>=mid) a[k++] = aux[y++];
            else if (y>high) a[k++] = aux[x++];
            else if (less(aux, x, y)) a[k++] = aux[x++];
            else a[k++] = aux[y++];
        }
    }
}
