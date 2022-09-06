package com.hef.review.algorithm.sort;

/**
 * @Date 2022/9/6
 * @Author lifei
 */
public class ReviewHeapSort<T extends Comparable<T>> extends ReviewSort<T> {
    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) return;
        int k = a.length/2;
        int n = a.length;
        while (k>0) sink(a, k--, n);

        while (n>1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }
    }

    private void sink(T[] a, int k, int n) {
        while (k * 2 <= n) {
            int j = k * 2;
            if (j + 1 <= n && less(a, j, j+1)) j++;
            if (!less(a, k, j)) return;
            exch(a, j, k);
            k = j;
        }
    }

    @Override
    public boolean less(T[] a, int i, int j) {
        return a[i-1].compareTo(a[j-1]) < 0;
    }

    @Override
    public void exch(T[] a, int i, int j) {
        T t = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = t;
    }


}
