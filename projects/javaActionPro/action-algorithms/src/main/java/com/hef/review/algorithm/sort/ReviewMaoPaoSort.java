package com.hef.review.algorithm.sort;

/**
 * @Date 2022/9/7
 * @Author lifei
 */
public class ReviewMaoPaoSort<T extends Comparable<T>> extends ReviewSort<T>{

    @Override
    public void sort(T[] a) {
        for (int i=0; i<a.length-1; i++) {
            for (int j=0; j+1<a.length - i; j++) {
                if (less(a, j+1, j)) exch(a, j, j+1);
            }
        }
    }
}
