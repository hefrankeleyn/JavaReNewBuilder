package com.hef.review1.tree;

import java.util.Arrays;
import java.util.Random;

/**
 * 二分查询树
 * @Date 2021/10/3
 * @Author lifei
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;
    private int size;

    private class Node {
        Key key;
        Value val;
        Node left;
        Node right;
        int N;
        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            this.N = n;
        }
    }

    /**
     * 查询树的大小
     * @return
     */
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        return node==null?0:node.N;
    }

    /**
     * 添加元素
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node node, Key key, Value val) {
        if (node==null) {
            return new Node(key, val, 1);
        }else {
            int com = key.compareTo(node.key);
            if (com==0) {
                node.val = val;
            }else if (com>0) {
                node.right = put(node.right, key, val);
            }else {
                node.left = put(node.left, key, val);
            }
            node.N = size(node.left) + size(node.right) + 1;
            return node;
        }
    }

    public Value get(Key key) {
        Node node = get(root, key);
        return node==null?null:node.val;
    }

    private Node get(Node node, Key key) {
        if (node==null) {
            return null;
        }
        int com = key.compareTo(node.key);
        if (com==0) {
            return node;
        }else if (com>0) {
            return get(node.right, key);
        }else {
            return get(node.left, key);
        }
    }

    public void search() {
        searchIn(root);
    }

    private void searchIn(Node node) {
        if (node==null) {
            return;
        }
        searchIn(node.left);
        System.out.print(String.format("%d : %s, ", node.key, node.val));
        searchIn(node.right);
    }


    public static void main(String[] args) {
//        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
        AVLTree<Integer, String> bst = new AVLTree<>();
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
        bst.search();
        System.out.println();

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
