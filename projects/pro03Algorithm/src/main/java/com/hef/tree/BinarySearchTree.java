package com.hef.tree;

import java.util.Random;

/**
 * 二分查询树
 * @Date 2021/9/9
 * @Author lifei
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;

    class Node{
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node==null) return 0;
        else return node.N;
    }

    public void put(Key key, Value val) {
        if (root==null) {
            root = new Node(key, val, 1);
        }else {
            root = put(root, key, val);
        }
    }

    public Node put(Node node, Key key, Value val) {
        if (node==null) return new Node(key, val, 1);
        int com = key.compareTo(node.key);
        if (com<0) {
            node.left = put(node.left, key, val);
        }else if (com>0) {
            node.right = put(node.right, key, val);
        }else {
            node.val = val;
        }
        node.N = size(node.left)  + size(node.right) + 1;
        return node;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node==null) return null;
        int com = key.compareTo(node.key);
        if (com<0) {
            return get(node.left, key);
        }else if (com>0) {
            return get(node.right, key);
        }else {
            return node.val;
        }
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
        System.out.print(node.key + ":" + node.val + ":" + size(node) + ", ");
//        System.out.print(node.key + ", ");
        search(node.right);
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer, String> binarySearchTree = new BinarySearchTree<>();
        Random random = new Random(23);
        for (int i = 0; i < 21; i++) {
            int key = random.nextInt(23);
            int value = random.nextInt(100);
            binarySearchTree.put(key, value+"");
        }
        binarySearchTree.search();
    }
}
