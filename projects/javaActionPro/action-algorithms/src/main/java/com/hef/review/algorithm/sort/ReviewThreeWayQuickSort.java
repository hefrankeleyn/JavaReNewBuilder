package com.hef.review.algorithm.sort;
import java.util.Random;
/**
 * @Date 2022/9/13
 * @Author lifei
 */
public class ReviewThreeWayQuickSort<T extends Comparable<T>> extends ReviewSort<T>{

    @Override
    public void sort(T[] a) {
        if (a==null || a.length==0) return;

        shuffle(a);

        sort(a, 0, a.length-1);
    }

    private void sort(T[] a, int low, int high) {
        if (low>=high) return;
        int left = low+1, right = high, lt = low;
        while (left<=right) {
            int com = a[left].compareTo(a[lt]);
            if (com==0) left++;
            else if (com<0) exch(a, left++, lt++);
            else exch(a, left, right--);
        }
        sort(a, low, lt-1);
//        sort(a, left, high);
        sort(a, right+1, high);
    }

    private void shuffle(T[] a) {
        Random random = new Random(23);
        for (int i=0; i<a.length; i++) {
            exch(a, i, i + random.nextInt(a.length-i));
        }
    }
}
