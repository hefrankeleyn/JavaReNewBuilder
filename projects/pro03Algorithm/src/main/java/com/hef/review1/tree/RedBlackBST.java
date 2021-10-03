package com.hef.review1.tree;

import java.util.Random;

/**
 * @Date 2021/10/3
 * @Author lifei
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node{
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean color;

        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            this.N = n;
            this.color = color;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node==null) return 0;
        return node.N;
    }

    private boolean isRed(Node node) {
        if (node==null) return false;
        return node.color == RED;
    }

    /**
     * 左旋
     * @param node
     * @return
     */
    private Node leftRotate(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        x.N = node.N;
        node.N = size(node.left) + size(node.right) + 1;
        return x;
    }

    /**
     * 右旋
     * @param node
     * @return
     */
    private Node rightRotate(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.N = node.N;
        node.N = size(node.left) + size(node.right) + 1;
        return x;
    }

    /**
     * 翻转颜色
     * @param node
     */
    private void flipColor(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        // 根节点始终为黑色
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value val) {
        if (node==null) {
            return new Node(key, val, 1, RED);
        }

        int com = key.compareTo(node.key);
        if (com==0) node.val = val;
        else if (com>0) node.right = put(node.right, key, val);
        else node.left = put(node.left, key, val);

        node.N = size(node.left) + size(node.right) + 1;

        if (isRed(node.right) && !isRed(node.left)) node = leftRotate(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rightRotate(node);
        if (isRed(node.left) && isRed(node.right)) flipColor(node);
        return node;
    }

    public Value get(Key key) {
        Node node = get(root, key);
        return node==null?null:node.val;
    }

    private Node get(Node node, Key key) {
        if (node==null) return null;
        int com = key.compareTo(node.key);
        if (com==0) return node;
        else if (com>0) return get(node.right, key);
        else return get(node.left, key);
    }

    public static void main(String[] args) {
        RedBlackBST<Integer, String> bst = new RedBlackBST<>();
        Integer[] keys = createKeys(9);
        for (Integer k: keys) {
            bst.put(k, k+"aaa");
        }
        System.out.println(bst);
        System.out.println("Size: " + bst.size());
        Integer k = 1;
        long start = System.nanoTime();
        String oneV = bst.get(k);
        long end = System.nanoTime();
        // aaa1:aaa1, 14s
        // 1:1aaa, 72s
        System.out.println(String.format("%s:%s, %d", k, oneV, (end-start)/1000));
    }


    private static Integer[] createKeys(int num) {
        Integer[] keys = new Integer[num];
        for (int i = 0; i < num; i++) {
            keys[i] = i;
        }
//        shuffle(keys);
        return keys;
    }

    private static void shuffle(Comparable[] a) {
        Random random = new Random(23);
        int n = a.length;
        for (int i=0; i<n; i++) {
            int j = random.nextInt(n-i);
            exch(a, i, j);
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
