package com.hef.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 平衡二叉树
 * @Date 2021/9/11
 * @Author lifei
 */
public class AVLTree<Key extends Comparable<Key>, Value> {

    private Node root;

    class Node {
        private Key key;
        private Value val;
        private int N;

        private Node left;
        private Node right;

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
        return node.N;
    }


    public void put(Key key, Value val) {
        if (root==null) {
            root = new Node(key, val, 1);
        }else {
            root = put(root, key, val);
        }
    }

    private Node put(Node node, Key key, Value val) {
        if (node==null) return new Node(key, val, 1);
        int com = key.compareTo(node.key);
        if (com>0) {
            node.right = put(node.right, key, val);
        }else if (com<0) {
            node.left = put(node.left, key, val);
        }else {
            node.val = val;
        }
        node.N = size(node.left) + size(node.right) + 1;

        if (size(node.left) - size(node.right)>1) {
            if (size(node.left.right)>size(node.left.left)) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        }else if (size(node.right) - size(node.left)>1) {
            if (size(node.right.left) > size(node.right.right)) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }
        return node;
    }

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


    /**
     * 向左旋转
     * @param h
     * @return
     */
    public Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * 向右旋转
     * @param h
     * @return
     */
    public Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }



    public void bfs(Node node) {
        if (node==null) return;
        Deque<Node> deque = new ArrayDeque<>();
        Deque<Node> deque2 = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            Node currentNode = deque.removeLast();
            if (currentNode==null) {
                System.out.print("  ");
            }else {
                System.out.print(currentNode.key + " ");
            }
            if (currentNode.left!=null) {
                deque2.addFirst(currentNode.left);
            }
            if (currentNode.right!=null) {
                deque2.addFirst(currentNode.right);
            }
            if (deque.isEmpty()) {
                System.out.println();
                deque = deque2;
                deque2 = new ArrayDeque<>();
            }
        }
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

    }

    private static Integer[] createKeys(int num) {
        Integer[] keys = new Integer[num];
        for (int i = 0; i < num; i++) {
            keys[i] = i;
        }
//        shuffle(keys);
        return keys;
    }


}
