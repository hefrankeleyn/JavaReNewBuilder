package com.hef.recursion;

/**
 * @Date 2021/10/24
 * @Author lifei
 */
public class Search {

    public static void main(String[] args) {
        int[] nums = {5, 1, 3};
        int target = 3;
        Search search = new Search();
        int res = search.search(nums, target);
        System.out.println(res);
    }

    public int search(int[] nums, int target) {
        int right = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i]==target) return i;
            if (i>0 && nums[i]<nums[i-1]) {
                right = i;
                break;
            }
        }
        int left = right;
        right = nums.length-1;
        while (left<=right) {
            int mid = left + (right-left)/2;
            if (nums[mid]==target) return mid;
            else if (nums[mid]>target) right = mid  - 1;
            else left = mid + 1;
        }
        return -1;
    }
}
