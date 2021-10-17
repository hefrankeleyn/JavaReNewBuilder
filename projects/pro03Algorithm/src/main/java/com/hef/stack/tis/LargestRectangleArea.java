package com.hef.stack.tis;

import java.util.*;
/**
 * @Date 2021/10/17
 * @Author lifei
 */
public class LargestRectangleArea {

    public static void main(String[] args) {
        LargestRectangleArea rectangleArea = new LargestRectangleArea();
        int[] heights = {2,1,5,6,2,3};
//        int[] heights = {2,4};
        int res = rectangleArea.largestRectangleArea(heights);
        System.out.println(res);
    }

    public int largestRectangleArea(int[] heights) {
        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(-1);
        int max=0;
        for (int i=0; i<=heights.length; i++) {
            if (i==heights.length || (deque.peek()!=-1 && heights[deque.peek()]>heights[i])){
                while ((i==heights.length && deque.peek()!=-1) || (deque.peek()!=-1 && heights[deque.peek()]>heights[i])) {
                    int h_i = deque.removeFirst();
                    while (deque.peek() != -1 && heights[deque.peek()] == heights[h_i]) h_i = deque.removeFirst();
                    int l_i = deque.peek();
                    int r_i = i;
                    int area = (r_i - l_i - 1) * heights[h_i];
                    max = Math.max(area, max);
                }
            }
            deque.addFirst(i);
        }
        return max;
    }
}
