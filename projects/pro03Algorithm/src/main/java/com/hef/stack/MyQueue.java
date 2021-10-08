package com.hef.stack;

/**
 * FIFO
 * @Date 2021/10/8
 * @Author lifei
 */
public class MyQueue<T> {

    private Node first;
    private Node last;
    private int N;

    private class Node{
        private T t;
        private Node next;

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

    public void enQueue(T t) {
        Node oldLast = last;
        last = new Node(t);
        if (isEmpty()) {
            first = last;
        }else {
            oldLast.next = last;
        }
        N++;
    }

    public T deQueue() {
        if (isEmpty()) return null;
        Node oldFirst = first;
        first = oldFirst.next;
        N--;
        return oldFirst.t;
    }

    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enQueue(23);
        myQueue.enQueue(21);
        myQueue.enQueue(20);
        myQueue.enQueue(18);
        myQueue.enQueue(19);
        while (!myQueue.isEmpty()) {
            System.out.println(myQueue.deQueue());
        }
    }
}
