package com.hef.stack;

/**
 * 大顶堆
 * @Date 2021/10/8
 * @Author lifei
 */
public class MaxHeap<Key extends Comparable<Key>> {

    private Key[] keys;
    private int N;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.keys = (Key[])new Comparable[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public void insert(Key k) {
        keys[++N] = k;
        swim(N);
    }

    public Key delMax() {
        if (isEmpty()) return null;
        Key res = keys[1];
        exch(1, N--);
        keys[N+1] = null;
        sink(1);
        return res;
    }

    private void sink(int k) {
        while (2*k<=N) {
            int j = 2 * k;
            if (j<N && less(j, j+1)) j++;
            if (less(j, k)) break;
            exch(k, j);
            k = j;
        }
    }

    /**
     * 上升
     */
    private void swim(int k) {
        while (k>1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private boolean less(int i, int j) {
        return keys[i].compareTo(keys[j])<0;
    }

    private void exch(int i, int j) {
        Key t = keys[i];
        keys[i] = keys[j];
        keys[j] = t;
    }

    public static void main(String[] args) {
        MaxHeap<Integer> heap = new MaxHeap<>(10);
        heap.insert(9);
        heap.insert(23);
        heap.insert(21);
        heap.insert(19);
        heap.insert(6);
        heap.insert(3);
        heap.insert(31);

        while (!heap.isEmpty()) {
            System.out.println(heap.delMax());
        }
    }
}
