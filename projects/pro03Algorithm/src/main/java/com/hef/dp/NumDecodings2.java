package com.hef.dp;

/**
 * @Date 2021/10/2
 * @Author lifei
 */
public class NumDecodings2 {

    public static void main(String[] args) {
        NumDecodings2 numDecodings2 = new NumDecodings2();
        String s = "2101";
        int res = numDecodings2.numDecodings(s);
        System.out.println(res);

    }

    public int numDecodings(String s) {
        int n = s.length();
        if (s.charAt(0)=='0') return 0;
        if (n==1) return 1;
        int pre = 1;
        int cur = 0;
        if (s.charAt(1)!='0') {
            cur = 1;
        }
        int tv1 = Integer.parseInt(s.substring(0, 2));
        if (tv1>0 && tv1<=26) {
            cur = cur+1;
        }
        for (int i=2; i<n; i++) {
            if (cur==0) return 0;
            int tv = 0;
            if (s.charAt(i)!='0') tv=cur;
            if (s.charAt(i-1)!='0') {
                int tv2 = Integer.parseInt(s.substring(i-1, i+1));
                if (tv2>0 && tv2<=26) {
                    tv += pre;
                }
            }
            pre = cur;
            cur = tv;
        }
        return cur;

    }
}
