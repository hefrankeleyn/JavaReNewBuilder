package com.hef.recursion;

import java.util.*;

/**
 * @Date 2021/10/21
 * @Author lifei
 */
public class Combine {

    public static void main(String[] args) {
        Arrays.asList(new int[]{1, 2});
        Deque<Character> deque = new LinkedList<>();
        String expression = "Y?0:9";
        String res = computeTernary(expression);
        System.out.println(res);
    }

    public static String computeTernary(String expression) {
        if (expression==null || expression.length()==0) return "";
        Deque<Character> deque = new LinkedList<>();
        char c = expression.charAt(expression.length()-1);
        for (int i=expression.length()-1; i>=0; i--) {
            char v = expression.charAt(i);
            if (v>='0' && v<='9') deque.addFirst(v);
            if (v=='Y' || v=='N') {
                char c1 = deque.pop();
                char c2 = deque.pop();
                c=v=='Y'?c1:c2;
                deque.addFirst(c);
            }
        }
        return String.valueOf(c);
    }
}
