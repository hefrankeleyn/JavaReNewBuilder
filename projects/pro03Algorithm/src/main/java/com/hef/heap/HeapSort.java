package com.hef.heap;

import java.util.Arrays;

/**
 * @Date 2021/10/17
 * @Author lifei
 */
public class HeapSort {

    public static void main(String[] args) {
        Integer[] a = {9, 8,4, 1,2, 9, 8,4, 1,2};
        System.out.println(Arrays.toString(a));
        HeapSort.sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(Comparable[] a) {
        if (a==null || a.length<=1) return;
        int N = a.length;
        for (int k=N/2; k>=1; k--) {
            sink(a, k, N);
        }
        while (N>1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (k*2<=N) {
            int j = k*2;
            if (j<N && less(a, j, j+1)) j++;
            if (less(a, j, k)) break;
            exch(a, k, j);
            k = j;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = t;
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i-1].compareTo(a[j-1])<0;
    }





}
