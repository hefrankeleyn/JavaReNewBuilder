package com.hef.sort;

import java.util.Arrays;

/**
 * 从上到下到归并排序
 * @Date 2021/10/30
 * @Author lifei
 */
public class MyUpDownMergeSort {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        for (int k=1; k<=a.length; k += k) {
            for (int i=0; i<a.length; i += k + k) {
                merge(a, i, i+k, Math.min(i+k+k-1, a.length-1));
            }
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        for (int k=lo; k<=hi; k++) {
            aux[k] = a[k];
        }
        int i=lo, j = mid;
        for (int k=lo; k<=hi; k++) {
            if (i>=mid) a[k] = aux[j++];
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
        MyUpDownMergeSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
