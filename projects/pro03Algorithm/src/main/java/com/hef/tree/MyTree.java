package com.hef.tree;

import java.util.Random;

/**
 * @Date 2021/9/8
 * @Author lifei
 */
public class MyTree<T extends Comparable<T>> {

    private MyNode root;
    private int size;

    class MyNode {
        T t;
        MyNode left;
        MyNode right;
        public MyNode(T t) {
            this.t = t;
        }
    }

    public void insert(T t) {
        if (t==null) return;
        if (isEmpty()) {
            root = new MyNode(t);
        }else {
            insert(root, t);
        }
        size++;
    }

    private void insert(MyNode node, T t) {
        if (node.t.compareTo(t)<0) {
            if (node.right==null) {
                node.right = new MyNode(t);
            }else {
                insert(node.right, t);
            }
        }else if (node.t.compareTo(t)>0) {
            if (node.left==null) {
                node.left = new MyNode(t);
            }else {
                insert(node.left, t);
            }
        }
    }

    public void search() {
        System.out.println("前序遍历：");
        proSearch(root);
        System.out.println("中序遍历：");
        midSearch(root);
        System.out.println("后序遍历：");
        bakSearch(root);
        System.out.println();
    }

    private void proSearch(MyNode node) {
        if (node==null) return;
        System.out.print(node.t + " ");
        proSearch(node.left);
        proSearch(node.right);
    }

    private void midSearch(MyNode node) {
        if (node==null) return;
        midSearch(node.left);
        System.out.print(node.t + " ");
        midSearch(node.right);
    }

    private void bakSearch(MyNode node) {
        if (node==null) return;
        bakSearch(node.left);
        bakSearch(node.right);
        System.out.print(node.t + " ");
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public static void main(String[] args) {
        MyTree<Integer> myTree = new MyTree<>();
        Random random = new Random(23);
        for (int i = 0; i < 10; i++) {
            int v = random.nextInt(100);
            myTree.insert(v);
        }
        myTree.search();
    }
}
