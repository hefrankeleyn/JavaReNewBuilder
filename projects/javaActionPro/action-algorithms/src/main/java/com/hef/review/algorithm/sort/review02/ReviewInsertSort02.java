package com.hef.review.algorithm.sort.review02;

import com.hef.review.algorithm.sort.ReviewSort;

/**
 * @Date 2022/9/18
 * @Author lifei
 */
public class ReviewInsertSort02<T extends Comparable<T>> extends ReviewSort<T> {

    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) {
            return;
        }
        for (int i=1; i<a.length; i++) {
            T t = a[i];
            int j = i;
            for ( ; j>=1; j--) {
                if (less(t, a[j-1])) a[j] = a[j-1];
                else break;
            }
            a[j] = t;
        }
    }

    private boolean less(T a, T b) {
        return a.compareTo(b)<0;
    }
}
