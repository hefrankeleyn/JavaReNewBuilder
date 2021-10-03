package com.hef.review1.tree;

/**
 * 平衡二分查询树
 * @Date 2021/10/3
 * @Author lifei
 */
public class AVLTree <Key extends Comparable<Key>, Value>{

    private Node root;

    private class Node{
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
     * 树的大小
     * @return
     */
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        return node==null?0:node.N;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node node, Key key, Value val) {
        if (node==null) {
            return new Node(key, val, 1);
        }
        int com = key.compareTo(node.key);
        if (com==0) {
            node.val = val;
        }else if (com>0) {
            node.right = put(node.right, key, val);
        }else {
            node.left = put(node.left, key, val);
        }
        node.N = size(node.left) + size(node.right) + 1;

//        if (size(node.left)>size(node.right)) {
        if (size(node.left)-size(node.right)>1) {
            if (size(node.left.left)<size(node.left.right)) {
                node.left = leftRote(node.left);
            }
            node = rightRote(node);
//        }else if (size(node.left)<size(node.right)) {
        }else if (size(node.right)-size(node.left)>1) {
            if (size(node.right.left)>size(node.right.right)) {
                node.right = rightRote(node.right);
            }
            node = leftRote(node);
        }
        return node;
    }

    public Value get(Key key) {
        Node node = get(root, key);
        return node==null?null:node.val;
    }

    private Node get(Node node, Key key) {
        if (node==null) return null;
        int com = key.compareTo(node.key);
        if (com==0) {
            return node;
        } else if (com>0) {
            return get(node.right, key);
        }else {
            return get(node.left, key);
        }
    }

    /**
     * 左旋
     * @param node
     */
    private Node leftRote(Node node) {
        Node t = node.right;
        node.right = t.left;
        t.left = node;
        t.N = node.N;
        node.N = size(node.left) + size(node.right) + 1;
        return t;
    }

    private Node rightRote(Node node) {
        Node t = node.left;
        node.left = t.right;
        t.right = node;
        t.N = node.N;
        node.N = size(node.left) + size(node.right) + 1;
        return t;
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
}
