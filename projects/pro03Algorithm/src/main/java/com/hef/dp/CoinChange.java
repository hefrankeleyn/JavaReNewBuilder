package com.hef.dp;

import java.util.*;
/**
 * @Date 2021/9/21
 * @Author lifei
 */
public class CoinChange {

    public static void main(String[] args) {
//        int[] coins = {1,2147483647};
//        int amount = 2;
        int[] coins = {2};
        int amount = 3;
        int res = coinChange(coins, amount);
        System.out.println(res);
    }

    public static int coinChange(int[] coins, int amount) {
        // 解法三：动态规划
        int[] dp = new int[amount+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i=1; i<=amount; i++) {
            for (int j=0; j<coins.length; j++) {
                if (i-coins[j]>=0 && dp[i-coins[j]]>=0) {
                    if (dp[i]==-1) {
                        dp[i] = dp[i-coins[j]]+1;
                    }else {
                        dp[i] = Math.min(dp[i-coins[j]]+1, dp[i]);
                    }

                }
            }
        }
        return dp[amount];
    }

    public static int coinChange2(int[] coins, int amount) {
        // // 解法一：DFS
        // return dfs(amount, coins, 0);

        // 解法二： BFS
        Deque<Integer> deque = new LinkedList<>();
        deque.addLast(amount);
        int res = 0;
        while(!deque.isEmpty()) {
            int n = deque.size();
            for (int i=0; i<n; i++) {
                int item = deque.removeFirst();
                if (item==0) return res;
                for (int j=0; j<coins.length; j++) {
                    if (item-coins[j]>=0) {
                        deque.addLast(item-coins[j]);
                    }
                }
            }
            res++;
        }
        return -1;
    }

    public static int coinChange1(int[] coins, int amount) {
        // // 解法一：DFS
        // return dfs(amount, coins, 0);

        // 解法二： BFS
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(amount);
        Deque<Integer> deque2 = new ArrayDeque<>();
        int res = 0;
        while(!deque.isEmpty()) {
            int item = deque.removeFirst();
            if (item==0) return res;
            else if (item<0 && !deque.isEmpty()) {
                item = deque.removeFirst();
            }
            for (int i=0; i<coins.length; i++) {
                deque2.addLast(item-coins[i]);
            }
            if (deque.isEmpty()) {
                res++;
                deque = deque2;
                deque2 = new ArrayDeque<>();
            }
        }
        return -1;
    }

    // 解法一：DFS
    private int dfs(int amount, int[] coins, int oneRes) {
        if (amount<=0) {
            if (0==amount) {
                return oneRes;
            }
            return -1;
        }
        int res = -1;
        for (int i=0; i<coins.length; i++) {
            int t = dfs(amount-coins[i], coins, oneRes+1);
            if (t>=0 && res==-1) {
                res = t;
            }else if (t>0) {
                res = Math.min(t, res);
            }
        }
        return res;
    }
}
