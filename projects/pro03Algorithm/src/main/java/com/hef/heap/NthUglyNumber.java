package com.hef.heap;

import java.util.*;

/**
 * @Date 2021/10/17
 * @Author lifei
 */
public class NthUglyNumber {

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(5);
        queue.add(3);
        queue.add(6);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        /*int n = 11;
        NthUglyNumber nthUglyNumber = new NthUglyNumber();
        int res = nthUglyNumber.nthUglyNumber(n);
        System.out.println(res);*/
    }

    public int nthUglyNumber(int n) {
        int[] a = {2, 3, 5};
        PriorityQueue<Long> queue = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        queue.add(1l);
        visited.add(1l);
        long res = 1;
        for (int i=1; i<=n; i++) {
            res = queue.poll();
            for (int k=0; k<a.length; k++) {
                if (visited.add(a[k]*res)) {
                    queue.add(a[k]*res);
                }
            }
        }
        return (int)res;
    }
}
