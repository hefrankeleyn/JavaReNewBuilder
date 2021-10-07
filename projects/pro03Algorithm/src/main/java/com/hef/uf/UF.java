package com.hef.uf;

public interface UF {

    /**
     * 朋友圈的个数
     * @return
     */
    int count();

    /**
     * 将p和 q 归为同一个朋友圈
     * @param p
     * @param q
     */
    void union(int p, int q);

    /**
     * 查找p所在的朋友圈
     * @param p
     * @return
     */
    int find(int p);

    /**
     * 判断 p 和 q 是否在相同的朋友圈
     * @param p
     * @param q
     * @return
     */
    boolean connected(int p, int q);

}
