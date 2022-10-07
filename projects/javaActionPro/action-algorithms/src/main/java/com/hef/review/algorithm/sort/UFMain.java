package com.hef.review.algorithm.sort;

import java.util.Arrays;

/**
 * @Date 2022/9/20
 * @Author lifei
 */
public class UFMain {

    public static void main(String[] args) {
        String[] a = new String[]{
                "4 3",
                "3 8",
                "6 5",
                "9 4",
                "2 1",
                "5 0",
                "7 2",
                "6 1",
        };
//        UF uf = new QuickFindUF(10);
//        UF uf = new QuickUnionUF(10);
        UF uf = new QuickUnionWeightUF(10);
        for (String s : a) {
            int x = Integer.parseInt(s.split(" ")[0]);
            int y = Integer.parseInt(s.split(" ")[1]);
            uf.union(x, y);
        }
        System.out.println(uf.count());
    }
}
