package com.hef.stack;

import java.util.*;
/**
 * @Date 2021/10/8
 * @Author lifei
 */
public class SkipList<T> {

    private SkipNode head;
    private int N;
    private Random random;
    private int highLevel;
    private static final int MAX_LEVEL = 32;

    public SkipList() {
        this.highLevel = 0;
        head = new SkipNode(Integer.MIN_VALUE, null);
        random = new Random();
    }

    private class SkipNode {
        private int key;
        private T value;
        private SkipNode right, down;
        public SkipNode(int key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public SkipNode searchNode(int key) {
        SkipNode team = head;
        while(team!=null) {
            if (team.key==key) {
                return team;
            }else if (team.right==null || team.right.key>key) {
                team = team.down;
            }else {
                team = team.right;
            }
        }
        return null;
    }

    public void deleteNode(int key) {
        SkipNode team = head;
        while (team!=null) {
            if (team.right==null || team.right.key>key) {
                team = team.down;
            }else if (team.right.key<key) {
                team = team.right;
            }else if (team.right.key==key) {
                team.right = team.right.right;
                team = team.down;
                N--;
            }
        }
    }

    public void insertNode(int key, T value) {
        insertNode(new SkipNode(key, value));
    }

    public void insertNode(SkipNode node) {
        SkipNode searchNode = searchNode(node.key);
        if (searchNode!=null) {
            searchNode.value = node.value;
            return;
        }
        MyStack<SkipNode> stack = new MyStack<>();
        SkipNode team = head;
        while (team!=null) {
            if (team.right==null || team.right.key>node.key) {
                stack.push(team);
                team = team.down;
            }else {
                team = team.right;
            }
        }
        // 当前层数
        int level = 0;
        SkipNode downNode = null;
        while (!stack.isEmpty()) {
            SkipNode leftNode = stack.pop();
            // 插入左侧节点
            SkipNode oneNode = new SkipNode(node.key, node.value);
            oneNode.down = downNode;
            downNode = oneNode;
            if (leftNode.right!=null) {
                oneNode.right = leftNode.right;
            }
            leftNode.right = oneNode;
            // 考虑是否向上
            if (level>MAX_LEVEL) break;
            double num = random.nextDouble();
            if (num>0.5) break;
            level++;
            if (level>highLevel) {
                highLevel = level;
                // 创建一个新节点
                SkipNode oneHead = new SkipNode(Integer.MIN_VALUE, null);
                oneHead.down = head;
                head = oneHead;
                stack.push(head);
            }
        }

        N++;

    }


    public static void main(String[] args) {
        SkipList<String> skipList = new SkipList<>();
        int[] keys = {6, 9, 1, 4, 2, 3, 0, 5};
        for (int i=0; i<keys.length; i++) {
            skipList.insertNode(keys[i], "aa"+keys[i]);
        }
        System.out.println(skipList.size());
        System.out.println(skipList);
    }
}
