package com.hef.demo;

import java.util.*;
/**
 * @Date 2021/11/9
 * @Author lifei
 */
public class FindSameSnow {

    public static void main(String[] args) {
        FindSameSnow findSameSnow = new FindSameSnow();
        int[][] snows = {
                {1,2,3,4,5,6},
                {4,2,3,1,6,5}
        };
        String res = findSameSnow.findSameSnow(snows);
        System.out.println(res);

    }

    public String findSameSnow(int[][] snows) {
        Set<String> all = new HashSet<>();
        for (int i=0; i<snows.length; i++) {
            Set<String> set = mk(snows[i]);
            for (String s: set){
                if (all.contains(s)) return "Yes";
            }
            all.addAll(set);
        }
        return "No";
    }

    private Set<String> mk(int[] a) {
        Set<String> res = new HashSet<>();
        int k=0;
        for (int i=0; i<a.length; i++) {
            if (a[i]<a[k]) {
                k=i;
            }
        }
        int t = k;
        StringBuilder sb = new StringBuilder();
        sb.append(a[k++]);
        while (k!=t) {
            if (k>=a.length) {
                k=0;
            }else {
                sb.append(a[k++]);
            }
        }
        res.add(sb.toString());
        sb.delete(0, a.length);
        k=t;
        sb.append(a[k--]);
        while (k!=t){
            if (k<0) {
                k=a.length-1;
            }else {
                sb.append(a[k--]);
            }
        }
        res.add(sb.toString());
        return res;
    }
}
