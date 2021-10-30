package com.hef.sort;

import java.util.Arrays;

/**
 * @Date 2021/10/30
 * @Author lifei
 */
public class MyMaoPaoSort {

    public static void sort(Comparable[] a) {
        for (int i=0; i<a.length-1; i++) {
            for (int j=1; j<a.length-i; j++) {
                if (less(a, j, j-1)) exch(a, j, j-1);
            }
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j])<0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Integer[] a = {9,  10, 0, 12, 4, 7, 1, 0, 10, 9,  10, 0, 12, 4, 7, 1, 0, 10};
        System.out.println(Arrays.toString(a));
        MyMaoPaoSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
