package com.hef.stack;

import java.util.Stack;

/**
 * 栈：FILO
 * @Date 2021/10/8
 * @Author lifei
 */
public class MyStack<T> {

    private Node first;
    private int N;

    private class Node {
        T t;
        Node next;
        public Node(T t) {
            this.t = t;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public void push(T t) {
        Node oldFirst = first;
        first = new Node(t);
        first.next = oldFirst;
        N++;
    }

    public T pop() {
        if (isEmpty()) return null;
        Node oldFirst = first;
        first = oldFirst.next;
        N--;
        return oldFirst.t;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(23);
        stack.push(3);
        stack.push(15);
        stack.push(5);
        stack.push(9);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
