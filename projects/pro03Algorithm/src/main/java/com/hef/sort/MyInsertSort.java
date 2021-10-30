package com.hef.sort;

import java.util.Arrays;

/**
 * @Date 2021/10/30
 * @Author lifei
 */
public class MyInsertSort {

    public static void sort(Comparable[] a) {
        for (int i=1; i<a.length; i++) {
            Comparable v = a[i];
            int j = i-1;
            for (; j>=0; j--) {
                if (less(v, a[j])) a[j+1] = a[j];
                else break;
            }
            a[j+1] = v;
        }
    }

    private static boolean less(Comparable v1, Comparable v2) {
        return v1.compareTo(v2)<0;
    }

    public static void main(String[] args) {
        Integer[] a = {9,  10, 0, 12, 4, 7, 1, 12, 4, 7, 1, 0, 10, 9,  10, 0, 12, 0, 10, 9,  10, 0, 12, 4, 7, 1, 0, 10};
        System.out.println(Arrays.toString(a));
        MyInsertSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
