package com.hef.review.algorithm.sort;

/**
 * @Date 2022/9/20
 * @Author lifei
 */
public class QuickUnionWeightUF implements UF{

    private int[] a;
    private int[] sz;
    private int count;
    public QuickUnionWeightUF(int n) {
        this.count = n;
        this.a = new int[n];
        this.sz = new int[n];
        for (int i=0; i<a.length; i++) {
            this.a[i] = i;
            this.sz[i] = 1;
        }
    }
    @Override
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId==qId) return;
        if (sz[pId]>sz[qId]) {
            a[qId] = pId;
            sz[pId] += sz[qId];
        } else {
            a[pId] = qId;
            sz[qId] += sz[pId];
        }
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
