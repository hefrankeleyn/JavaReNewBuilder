package com.hef.dp;

/**
 * 如果第一可以上 1、2、3 台阶，总共有多少种方法
 * @Date 2021/9/15
 * @Author lifei
 */
public class ClimbStairs2 {

    // | - - -

    public static void main(String[] args) {
        int n = 9;
        int res = solve(n);
        System.out.println(res);

    }

    /**
     * 爬楼梯: 一次可以上 1、2、3 阶台阶
     * @param n
     * @return
     */
    public static int solve(int n) {
        if (n<=2) return n;
        if (n==3) return 4;
        int x = 1, y = 2, z = 4, res = 0;
        for (int i=4; i<=n; i++) {
            res = x + y + z;
            x = y;
            y = z;
            z = res;
        }
        return res;
    }

}
