package com.hef.demo;

import java.util.*;
/**
 * @Date 2021/11/9
 * @Author lifei
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.poll();
        StringBuilder sb = new StringBuilder();
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] T = {73,74,75,71,69,72,76,73};
        int[] res = dailyTemperatures.dailyTemperatures(T);
        System.out.println(Arrays.toString(res));
    }

    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        Deque<Integer> deque = new LinkedList<>();
        for (int i=0; i<n; i++) {
            while (!deque.isEmpty() && T[deque.peek()]<T[i]) {
                int j = deque.pop();
                res[j] = i-j;
            }
            deque.push(i);
        }
        return res;
    }
}
