package com.hef.uf;


/**
 * @Date 2021/10/4
 * @Author lifei
 */
public class WeightedQuickUnionUF implements UF{

    private int count;
    private int[] id;
    private int[] sz;

    public WeightedQuickUnionUF(int count) {
        this.count = count;
        this.id = new int[count];
        this.sz = new int[count];
        for (int i=0; i<count; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        if (sz[pRoot]>sz[qRoot]) { id[qRoot] = pRoot; sz[pRoot] += sz[qRoot];}
        else { id[pRoot] = qRoot; sz[qRoot] += sz[pRoot]; }

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
