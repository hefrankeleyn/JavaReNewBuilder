package com.hef.dp;

/**
 * @Date 2021/10/2
 * @Author lifei
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        LongestValidParentheses parentheses = new LongestValidParentheses();
        String s = "()(())";
        int res = parentheses.longestValidParentheses(s);
        System.out.println(res);
    }

    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n<2) return 0;
        int[] dp = new int[n];
        int res = 0;
        for (int i=1; i<n; i++) {
            if (s.charAt(i)==')') {
                if (s.charAt(i-1)=='(') {
                    dp[i] = i-2>=0?dp[i-2]+2:2;
                }else if (dp[i-1]>0 && i-dp[i-1]-1>=0 && s.charAt(i-dp[i-1]-1)=='('){
                    dp[i] = dp[i-1]+(i-dp[i-1]-2>=0?dp[i-dp[i-1]-2]:0)+2;
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}
