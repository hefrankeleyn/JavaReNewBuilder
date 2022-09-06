package com.hef.review.algorithm.sort;

/**
 * @Date 2022/9/5
 * @Author lifei
 */
public class ReviewSelectorSort<T extends Comparable> extends ReviewSort<T>{

    // 每次选择最小的值放到最前面
    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) return;

        for (int i=0; i<a.length-1; i++) {
            int k = i;
            for (int j=i+1; j<a.length; j++) {
                if (less(a, j, k)) k = j;
            }
            exch(a, k, i);
        }
    }

    // 每次选择最大的一个元素放到最后
    /*@Override
    public void sort(T[] a) {
        if (a==null || a.length==0) {
            return;
        }
        for (int i=0; i<a.length-1; i++) {
            int k = 0, j=1;
            for (j = 1; j<a.length-i; j++) {
                if (less(a, k, j)) {
                    k = j;
                }
            }
            exch(a, k, j-1);
        }
    }*/
}
