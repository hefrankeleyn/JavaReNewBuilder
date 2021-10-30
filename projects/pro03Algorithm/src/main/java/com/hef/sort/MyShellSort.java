package com.hef.sort;

import java.util.Arrays;

/**
 * @Date 2021/10/30
 * @Author lifei
 */
public class MyShellSort {

    public static void sort(Comparable[] a) {
        int k = 1, N = a.length;
        while (k<N/3) k = k * 3 + 1;
        while (k>=1) {
            for (int i=k; i<a.length; i++) {
                Comparable v = a[i];
                int j = i-k;
                for (; j>=0 && less(v, a[j]); j-=k) {
                    a[j+k] = a[j];
                }
                a[j+k] = v;
            }
            k = k/3;
        }
    }

    private static boolean less(Comparable v1, Comparable v2) {
        return v1.compareTo(v2)<0;
    }

    public static void main(String[] args) {
        Integer[] a = {9,  10, 0, 12, 4, 7, 1, 12, 4, 7, 1, 0, 10, 9,  10, 0, 12, 0, 10, 9,  10, 0, 12, 4, 7, 1, 0, 10};
        System.out.println(Arrays.toString(a));
        MyShellSort.sort(a);
        System.out.println(Arrays.toString(a));
    }

}
