package com.hef.sort;

import java.util.Arrays;

/**
 * 从下到上到归并排序
 * @Date 2021/10/30
 * @Author lifei
 */
public class MyDownUpMergeSort {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo>=hi) return;
        int mid = (lo+hi)>>1;
//        int mid = lo + (hi-lo)/2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        for (int k=lo; k<=hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid+1;
        for (int k=lo; k<=hi; k++) {
            if (i>mid) a[k] = aux[j++];
            else if (j>hi) a[k] = aux[i++];
            else if (less(aux, i, j)) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j])<0;
    }

    public static void main(String[] args) {
        Integer[] a = {9,  10, 0, 12, 4, 7, 1, 12, 4, 7, 1, 0, 10, 9,  10, 0, 12, 0, 10, 9,  10, 0, 12, 4, 7, 1, 0, 10};
        System.out.println(Arrays.toString(a));
        MyDownUpMergeSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
