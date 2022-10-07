package com.hef.review.algorithm.sort;

/**
 * @Date 2022/9/20
 * @Author lifei
 */
public class QuickUnionUF implements UF{

    private int[] a;
    private int count;

    public QuickUnionUF(int n) {
        this.count = n;
        this.a = new int[n];
        for (int i=0; i<a.length; i++) {
            this.a[i] = i;
        }
    }
    @Override
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId==qId) return;

        a[pId] = qId;
        count--;
    }

    @Override
    public int find(int p) {
        while (p!=a[p]) {
            p = a[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return false;
    }

    @Override
    public int count() {
        return count;
    }
}
