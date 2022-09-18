package com.hef.review.algorithm.sort;

/**
 * @Date 2022/9/13
 * @Author lifei
 */
public class ReviewShellSort<T extends Comparable<T>> extends ReviewSort<T>{

    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) return;

        int k = 1;
        while (k < a.length/3) {
            k = k * 3 + 1;
        }

        while (k>=1) {
            for (int i=k; i<a.length; i++) {
                T t = a[i];
                int j = i - k;
                for (; j>=0; j-=k) {
                    if (less(t, a[j])) a[j+k] = a[j];
                    else break;
                }
                a[j+k] = t;
            }
            k = k/3;
        }
    }

    private boolean less(T v1, T v2) {
        return v1.compareTo(v2)<0;
    }
}
