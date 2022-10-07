package com.hef.review.algorithm.sort;

/**
 * @Date 2022/9/20
 * @Author lifei
 */
public interface UF {

    void union(int p, int q);
    int find(int p);
    boolean isConnected(int p, int q);

    int count();
}
