package com.hef.stack.tis;

/**
 * @Date 2021/10/17
 * @Author lifei
 */
public class MyCircularDeque {

    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(8);
//        ["MyCircularDeque","insertFront","getFront","isEmpty","deleteFront","insertLast","getRear","insertLast","insertFront","deleteLast","insertLast","isEmpty"]
//[[8],[5],[],[],[],[3],[],[7],[7],[],[4],[]]
        circularDeque.insertFront(5);
        System.out.println(circularDeque.getFront());
        System.out.println(circularDeque.isEmpty());
        System.out.println(circularDeque.deleteFront());
        System.out.println(circularDeque.insertLast(3));


    }

    private Node first, last;
    private int N;
    private int capacity;

    private class Node{
        private int value;
        private Node next,pre;
        public Node(int value) {
            this.value = value;
        }
    }

    public MyCircularDeque(int k) {
        this.capacity = k;
    }

    public boolean insertFront(int value) {
        if (isFull()) return false;
        Node node = new Node(value);
        if (isEmpty()) {
            node.next = node;
            node.pre = node;
            first = node;
            last = node;
        }else {
            Node oldFirst = first;
            first = node;
            first.next = oldFirst;
            oldFirst.pre = first;
            first.pre = last;
            last.next = first;
        }
        N++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) return false;
        Node node = new Node(value);
        if (isEmpty()) {
            node.next = node;
            node.pre = node;
            last = node;
            first = last;
        }else {
            Node oldLast = last;
            last = node;
            last.next = first;
            last.pre = oldLast;
            first.pre = last;
            oldLast.next = last;
        }
        N++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) return false;
        if (size()==1) {
            first=null;
            last =null;
        }else {
            Node oldFirst = first;
            first = oldFirst.next;
            first.pre = last;
            last.next = first;
        }
        N--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) return false;
        if (size()==1) {
            first = null;
            last = null;
        }else {
            Node oldLast = last;
            last = oldLast.pre;
            last.next = first;
            first.pre = last;
        }
        N--;
        return true;
    }

    public int getFront() {
        if (isEmpty()) return -1;
        return first.value;
    }

    public int getRear() {
        if (isEmpty()) return -1;
        return last.value;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size()==capacity;
    }
}
