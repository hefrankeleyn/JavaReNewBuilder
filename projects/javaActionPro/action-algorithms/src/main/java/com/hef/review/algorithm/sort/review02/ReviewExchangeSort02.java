package com.hef.review.algorithm.sort.review02;

import com.hef.review.algorithm.sort.ReviewSort;

/**
 * @Date 2022/9/18
 * @Author lifei
 */
public class ReviewExchangeSort02<T extends Comparable<T>> extends ReviewSort<T> {

    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) {
            return;
        }
        for (int i=0; i<a.length-1; i++) {
            for (int j=1; j<a.length-i; j++) {
                if (less(a, j, j-1)) exch(a, j, j-1);
            }
        }
    }
}
