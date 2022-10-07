package com.hef.review.algorithm.sort;

/**
 * @Date 2022/9/20
 * @Author lifei
 */
public class QuickFindUF implements UF{

    private int[] a;
    private int count;

    public QuickFindUF(int n) {
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

        for (int i=0; i<a.length; i++) {
            if (a[i]==pId) {
                a[i] = qId;
            }
        }
        count--;
    }

    @Override
    public int find(int p) {
        return a[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p)==find(q);
    }

    @Override
    public int count() {
        return count;
    }
}
