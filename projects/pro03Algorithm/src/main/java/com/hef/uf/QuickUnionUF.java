package com.hef.uf;

/**
 * @Date 2021/10/4
 * @Author lifei
 */
public class QuickUnionUF implements UF{

    private int count;
    private int[] id;
    public QuickUnionUF(int N) {
        this.count = N;
        this.id = new int[N];
        for (int i=0; i<N; i++) {
            id[i] = i;
        }
    }


    @Override
    public int count() {
        return count;
    }

    @Override
    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        if (pid==qid) return;
        id[pid] = qid;
        count--;
    }

    @Override
    public int find(int p) {
        while (p!=id[p]) p = id[p];
        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p)==find(q);
    }
}
