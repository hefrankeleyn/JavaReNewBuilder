package com.hef.review.algorithm.sort.review02;

import com.hef.review.algorithm.sort.ReviewSort;

/**
 * @Date 2022/9/14
 * @Author lifei
 */
public class ReviewSelectorSort02<T extends Comparable<T>> extends ReviewSort<T> {


    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) {
            return;
        }
        for (int i=0; i<a.length-1; i++) {
            int k=i;
            for (int j=i+1; j<a.length; j++) {
                if (less(a, j, k)) k=j;
            }
            exch(a, i, k);
        }
    }
}
