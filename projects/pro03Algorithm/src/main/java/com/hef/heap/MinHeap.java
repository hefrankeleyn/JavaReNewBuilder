package com.hef.heap;

/**
 * @Date 2021/10/17
 * @Author lifei
 */
public class MinHeap<T extends Comparable<T>> {

    private int capacity;
    private T[] a;
    private int N;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        a = (T[]) new Comparable[capacity+1];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public boolean isFull() {
        return size()==capacity;
    }

    public void push(T t) {
        if (isFull()) return;
        a[++N] = t;
        swim(N);
    }

    public T delMin() {
        if (isEmpty()) return null;
        T t = a[1];
        exch(1, N--);
        a[N+1] = null;
        sink(1);
        return t;
    }

    private void sink(int k) {
        while (2*k<=N) {
            int j = 2*k;
            if (j<N && less(j+1, j)) j++;
            if (less(k, j)) break;
            exch(j, k);
            k = j;
        }
    }

    private void swim(int k) {
        while (k>1 && less(k, k/2)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void exch(int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private boolean less(int i, int j) {
        return a[i].compareTo(a[j])<0;
    }

    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>(6);
        heap.push(3);
        heap.push(5);
        heap.push(1);
        heap.push(0);
        heap.push(9);
        heap.push(2);
        while (!heap.isEmpty()) {
            System.out.print(heap.delMin() + ", ");
        }
        System.out.println();
    }
}
