package com.hef.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Date 2021/10/30
 * @Author lifei
 */
public class MyQuickSortOf3Way {

    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo>=hi) return;
        int left = lo+1, right = hi, lt = lo;
        while (left<=right) {
            int com = a[left].compareTo(a[lt]);
            if (com==0) left++;
            else if (com>0) exch(a, left, right--);
            else exch(a, lt++, left++);
        }
        sort(a, lo, lt-1);
        sort(a, right+1, hi);
    }

    private static void shuffle(Comparable[] a) {
        Random random = new Random(23);
        for (int i=0; i<a.length; i++) {
            exch(a, i, random.nextInt(a.length-i)+i);
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Integer[] a = {9,  10, 0, 12, 4, 7, 1, 12, 4, 7, 1, 0, 10, 9,  10, 0, 12, 0, 10, 9,  10, 0, 12, 4, 7, 1, 0, 10};
        System.out.println(Arrays.toString(a));
        MyQuickSortOf3Way.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
