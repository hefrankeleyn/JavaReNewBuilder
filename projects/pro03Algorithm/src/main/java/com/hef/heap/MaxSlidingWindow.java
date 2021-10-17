package com.hef.heap;

import java.util.*;

/**
 * @Date 2021/10/17
 * @Author lifei
 */
public class MaxSlidingWindow {

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        MaxSlidingWindow window = new MaxSlidingWindow();
        int[] res = window.maxSlidingWindow(nums, k);
        System.out.println(res);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length-k+1];
        for (int i=0; i<nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()]<nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            int w = i - k + 1;
            if (deque.peek()<w) {
                deque.pop();
            }
            if (w>=0) {
                res[w] = nums[deque.peek()];
            }
        }
        return res;
    }
}
