package com.hef.dp;

/**
 * @Date 2021/10/1
 * @Author lifei
 */
public class MaxProfit5 {

    public static void main(String[] args) {
        MaxProfit5 maxProfit5 = new MaxProfit5();
        int k = 2;
        int[] prices = {2, 4, 1};
        int res = maxProfit5.maxProfit(k, prices);
        System.out.println(res);
    }

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n<2 || k<1) return 0;
        // dp[i][0] 第i次买， dp[i][1] 第i次卖
        int[][] dp = new int[k][2];
        for (int j=0;j<k; j++) {
            dp[j][0] = -prices[0];
            dp[j][1] = 0;
        }
        for (int i=1; i<n; i++) {
            dp[0][0] = Math.max(dp[0][0], -prices[i]);
            dp[0][1] = Math.max(dp[0][1], dp[0][0]+prices[i]);
            for (int j=1; j<k; j++) {
                dp[j][0] = Math.max(dp[j-1][1]-prices[i], dp[j][0]);
                dp[j][1] = Math.max(dp[j][1], dp[j][0]+prices[i]);
            }
        }
        return dp[k-1][1];

    }
}
