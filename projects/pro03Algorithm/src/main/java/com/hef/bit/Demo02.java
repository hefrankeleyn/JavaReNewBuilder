package com.hef.bit;
import java.util.*;
/**
 * @Date 2021/11/3
 * @Author lifei
 */
public class Demo02 {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {

        }
//        int[] a = {9,8,7,6,5,1,2,3,4,100};
        int[] a = {0,1,0,1};
        int res = fieldSum(a);
        System.out.println(res);
    }

    public static int fieldSum(int[] v) {
        Deque<Integer> deque = new LinkedList<>();
        int res=0;
        for (int i=0; i<v.length+1; i++) {
            int t = Integer.MAX_VALUE;
            if (i<v.length) t = v[i];
            while (!deque.isEmpty() && v[deque.peek()]<=t) {
                int pre = deque.pop();
                res += i-pre-1;
            }
            deque.push(i);
        }
        return res;
    }
}
