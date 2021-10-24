package com.hef.recursion;

import java.util.*;
/**
 * @Date 2021/10/23
 * @Author lifei
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        int n = 3;
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        List<String> res = generateParenthesis.generateParenthesis(n);
        System.out.println(res);
    }

    public List<String> generateParenthesis(int n) {
        List<String> result =new ArrayList<>();
        Deque<String> deque = new LinkedList<>();
        deque.addLast("");
        int level = 0, h = n * 2;
        while (level<h) {
            int size = deque.size();
            for (int i=0; i<size; i++) {
                String s = deque.pop();
                deque.addLast(s+"(");
                deque.addLast(s+")");
            }
            level++;
        }
        while(!deque.isEmpty()) {
            String s = deque.pop();
            if (valid(s)) {
                result.add(s);
            }
        }
        return result;
    }

    private boolean valid(String s) {
        Deque<Character> deque = new LinkedList<>();
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i)=='(') {
                deque.push(s.charAt(i));
            }else {
                if (deque.isEmpty() || deque.peek()!='(') return false;
                deque.pop();
            }
        }
        return deque.isEmpty();
    }
}
