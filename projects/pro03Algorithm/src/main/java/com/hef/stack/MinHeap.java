package com.hef.stack;

/**
 * 小顶堆
 * @Date 2021/10/8
 * @Author lifei
 */
public class MinHeap<Key extends Comparable<Key>> {


    private Key[] a;
    private int N;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        a = (Key[]) new Comparable[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public void insert(Key k) {
        a[++N] = k;
        swim(N);
    }

    public Key delMin() {
        if (isEmpty()) return null;
        Key k = a[1];
        exch(1, N--);
        a[N+1] = null;
        sink(1);
        return k;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j<N && less(j+1, j)) j++;
            if (less(k , j)) return;
            exch(k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while (k>1 && less(k, k/2)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private boolean less(int i, int j) {
        return a[i].compareTo(a[j])<0;
    }

    private void exch(int i, int j) {
        Key t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>(10);
        heap.insert(9);
        heap.insert(23);
        heap.insert(21);
        heap.insert(19);
        heap.insert(6);
        heap.insert(3);
        heap.insert(31);

        while (!heap.isEmpty()) {
            System.out.println(heap.delMin());
        }
    }
}
