package com.hef.review.algorithm.sort;

/**
 * @Date 2022/9/13
 * @Author lifei
 */
public class ReviewInsertSort<T extends Comparable<T>> extends ReviewSort<T>{

    @Override
    public void sort(T[] a) {
        for (int i=1; i<a.length; i++) {
            T t = a[i];
            int j=i-1;
            for (;j>=0;j--) {
                if (less(t, a[j])) a[j+1] = t;
                else break;
            }
            a[j+1] = t;
        }
    }

    private boolean less(T v1, T t2) {
        return v1.compareTo(t2)<0;
    }


}
