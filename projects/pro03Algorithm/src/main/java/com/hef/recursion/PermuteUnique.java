package com.hef.recursion;

import java.util.*;

/**
 * @Date 2021/10/23
 * @Author lifei
 */
public class PermuteUnique {

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        PermuteUnique permuteUnique = new PermuteUnique();
        List<List<Integer>> res = permuteUnique.permuteUnique(nums);
        System.out.println(res);

    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res =new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        solve(0, n, nums, res, new Integer[n]);
        return res;
    }

    private void solve(int l, int n, int[] nums, List<List<Integer>> res, Integer[] a) {
        if (l>=n) {
            res.add(Arrays.asList(a.clone()));
            return;
        }
        for (int j=0;j<nums.length;) {
            a[l] = nums[j];
            int[] nextNums = createNextNums(nums, j);
            solve(l+1, n, nextNums, res, a);
            j++;
            while (j<nums.length && nums[j]==nums[j-1]) j++;
        }

    }

    private int[] createNextNums(int[] nums, int j) {
        int[] a = new int[nums.length-1];
        int i=0;
        for (int k=0; k<nums.length; k++) {
            if (k==j) continue;
            a[i++] = nums[k];
        }
        return a;
    }
}
