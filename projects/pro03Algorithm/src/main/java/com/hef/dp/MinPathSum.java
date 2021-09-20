package com.hef.dp;

/**
 * @Date 2021/9/20
 * @Author lifei
 */
public class MinPathSum {

    public static void main(String[] args) {
        int[][] grid = {{0, 1}, {1, 0}};
        int res = solve(grid);
        System.out.println(res);
    }

    private static int solve(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int i=1; i<n; i++) {
            dp[i]=grid[0][i] + dp[i-1];
        }
        for (int i=1; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (j==0) dp[j] = grid[i][j] + dp[j];
                else dp[j] = grid[i][j] + Math.min(dp[j], dp[j-1]);
            }
        }
        return dp[n-1];
    }
}
