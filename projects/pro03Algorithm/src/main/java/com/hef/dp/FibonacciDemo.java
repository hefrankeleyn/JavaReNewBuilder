package com.hef.dp;

/**
 * 斐波那契 数列
 * @Date 2021/9/15
 * @Author lifei
 */
public class FibonacciDemo {

    public static void main(String[] args) {
        // 0,  1, 2, 3, 5, 8, 13, 21, 34, 65
        int n = 9;
//        int res = solve01(n);
//        System.out.println(res);
        for (int i = 0; i <= n; i++) {
            int res2 = solve02(i);
            System.out.print(res2+", ");
        }
        System.out.println();

        int res3 = solve03(n);
        System.out.println(res3);
        System.out.println(solve04(n));
    }

    /**
     * 递归
     * @param n
     * @return
     */
    public static int solve01(int n) {
        if (n<=2) return n;
        return solve01(n-1) + solve01(n-2);
    }

    /**
     * 递归+记忆化搜索
     * @param n
     * @return
     */
    public static int solve02(int n) {
        int[] a = new int[n+1];
        return solve02(n, a);
    }

    private static int solve02(int n, int[] a) {
        if (n<=2) return n;
        if (a[n]!=0) return a[n];
        a[n] = solve02(n-1, a) + solve02(n-2, a);
        return a[n];
    }

    /**
     * 动态递推
     * @param n
     * @return
     */
    public static int solve03(int n) {
        if (n<=2) return n;
        int x=1, y=2, res=0;
        for (int i=3; i<=n; i++) {
            res = x + y;
            x = y;
            y = res;
        }
        return res;
    }

    public static int solve04(int n) {
        if (n<=1) return n;
        int x=1, y=1, res=0;
        for (int i=2; i<=n; i++) {
            res = x + y;
            x = y;
            y = res;
        }
        return res;
    }


}
