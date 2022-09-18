package com.hef.review.algorithm.sort.review02;

import com.hef.review.algorithm.sort.ReviewSort;

/**
 * @Date 2022/9/18
 * @Author lifei
 */
public class ReviewShellSort02<T extends Comparable<T>> extends ReviewSort<T> {
    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) {
            return;
        }
        int k = 1;
        while (k<a.length/3) k = k*3 + 1;

        while (k>=1) {
            for (int i=k; i<a.length; i++) {
                T t = a[i];
                int j = i;
                for (;j>=k;j-=k) {
                    if (less(t, a[j-k])) a[j] = a[j-k];
                    else break;
                }
                a[j] = t;
            }
            k = k/3;
        }
    }

    private boolean less(T a, T b) {
        return a.compareTo(b)<0;
    }
}
