package com.hef.tree;

import java.util.Random;

/**
 * 红黑 二分查询树
 * @Date 2021/9/12
 * @Author lifei
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private int N;
        private boolean color;

        private Node left, right;

        public Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    public boolean isRed(Node node) {
        if (node==null) return false;
        return node.color = RED;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node==null) return 0;
        return node.N;
    }

    /**
     * 左旋操作
     * @param h
     * @return
     */
    public Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * 右旋操作
     * @param h
     * @return
     */
    public Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }


    /**
     * 翻转颜色，将两个红节点的子节点，变为黑色节点
     * @param h
     */
    public void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }


    /**
     * 插入操作
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        root = put(root, key, val);
        // 保证根节点为黑色
        root.color = BLACK;
    }

    /**
     * 向节点插入元素
     * @param node
     * @param key
     * @param val
     * @return
     */
    public Node put(Node node, Key key, Value val) {
        if (node==null) {
            return new Node(key, val, 1, RED);
        }

        int com = key.compareTo(node.key);
        if (com==0) node.val = val;
        else if (com>0) node.right = put(node.right, key, val);
        else node.left = put(node.left, key, val);
        // 旋转和翻转颜色
        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 查询操作
     * @param key
     * @return
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node==null) return null;
        int com = key.compareTo(node.key);
        if (com==0) return node.val;
        else if (com>0) return get(node.right, key);
        else return get(node.left, key);
    }

    public void search() {
        search(root);
        System.out.println();
    }

    /**
     * 中序遍历
     * 对于二分查询树来说，中序排序是有序的
     * @param node
     */
    public void search(Node node) {
        if (node==null) return;
        search(node.left);
//        System.out.print(node.key + ":" + node.val + ", ");
        System.out.print(node.key + ":" + node.val + ":" + node.color + ", ");
//        System.out.print(node.key + ", ");
        search(node.right);
    }

    public static void main(String[] args) {
        RedBlackBST<Integer, String> redBlackBST = new RedBlackBST<>();
        Random random = new Random(23);
        for (int i = 0; i < 21; i++) {
            int key = random.nextInt(23);
            int value = random.nextInt(100);
            redBlackBST.put(key, value+"");
        }

        redBlackBST.search();

    }
}
