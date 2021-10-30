package com.hef.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Date 2021/10/30
 * @Author lifei
 */
public class MyQuickSort {

    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int low, int high) {
        if (low>=high) return;
        int j = partition(a, low, high);
//        System.out.println(String.format("%d, %d, %d", low, j, high));
        sort(a, low, j-1);
        sort(a, j+1, high);
    }

    private static int partition(Comparable[] a, int low, int high) {
        int left = low, right = high+1;
        int j = low;
        while (left<=right) {
            while (less(a, ++left, j)) if (left==high) break;
            while (less(a, j, --right)) if (right==low) break;
            if (left>=right) break;
            exch(a, left, right);
        }
        exch(a, j, right);
        return right;
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j])<0;
    }



    private static void shuffle(Comparable[] a) {
        Random random = new Random(23);
        for (int i=0; i<a.length; i++) {
            random.nextInt(a.length-i);
            exch(a, i, i+ random.nextInt(a.length-i));
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
        MyQuickSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
