package com.hef.dp;

/**
 * @Date 2021/9/21
 * @Author lifei
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = {1,-1,1};
        int res = maxSubArray(nums);
        System.out.println(res);
    }

    public static int maxSubArray(int[] nums) {
        // 解法三： 分治
        return solve3(0, nums.length-1, nums);
    }

    // 解法三：分治
    private static int solve3(int lo, int hi, int[] nums) {
        // 递归终止条件
        if (lo==hi) return nums[lo];
        int mid = (hi + lo)/2;
        // 分成子问题
        int leftRes = solve3(lo, mid, nums);
        int rightRes = solve3(mid+1, hi, nums);

        // 处理当前层
        int t1 = 0;
        int max1 = nums[mid];
        for (int i=mid; i>=lo; i--) {
            t1 += nums[i];
            max1 = Math.max(t1, max1);
        }

        int t2 = 0;
        int max2=nums[mid+1];
        for (int i=mid+1; i<=hi; i++) {
            t2 += nums[i];
            max2 = Math.max(t2, max2);
        }

        return Math.max(Math.max(leftRes, rightRes), max1+max2);

    }
}
