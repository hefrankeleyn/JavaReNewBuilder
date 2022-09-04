package com.hef.review.algorithm.sort;

public abstract class ReviewSort<T extends Comparable> {

    public abstract void sort(T[] a);

    public boolean less(T[] a, int i, int j) {
        return a[i].compareTo(a[j])<0;
    }

    public void exch(T[] a, int i, int j) {
        T k = a[i];
        a[i] = a[j];
        a[j] = k;
    }
}
