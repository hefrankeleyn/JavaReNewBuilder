package com.hef.lur;

import java.util.*;
/**
 * @Date 2021/10/11
 * @Author lifei
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.first);
        cache.put(3, 3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
    }

    private Node first, last;
    private int capacity;
    private int N;
    private Map<Integer, Node> map = new HashMap<>();

    private class Node {
        private int key;
        private int value;
        private Node pre, next;
        public Node(){}
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        first = new Node();
        last = new Node();
        first.next = last;
        last.pre = first;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node==null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node!=null) {
            node.value = value;
            moveToHead(node);
        }else {
            node = new Node(key, value);
            addHead(node);
            map.put(key, node);
            N++;
            if (N>capacity) {
                Node tailNode = deleteTail();
                map.remove(tailNode.key);
                N--;
            }
        }
    }

    private Node deleteTail() {
        Node node = last.pre;
        deleteNode(node);
        return node;
    }

    private void moveToHead(Node node) {
        deleteNode(node);
        addHead(node);
    }

    private void deleteNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void addHead(Node node) {
        node.next = first.next;
        node.pre = first;
        first.next.pre = node;
        first.next = node;
    }
}
