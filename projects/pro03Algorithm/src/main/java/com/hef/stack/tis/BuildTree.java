package com.hef.stack.tis;

import java.util.*;
/**
 * @Date 2021/10/20
 * @Author lifei
 */
public class BuildTree {

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7}, inorder = {9,3,15,20,7};
        BuildTree buildTree = new BuildTree();
        TreeNode root = buildTree.buildTree(preorder, inorder);
        System.out.println(root);
    }

    public static class TreeNode {
        int val;
        TreeNode left,right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, 0, inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int p, int i1, int i2) {
        if (i1>i2) return null;
        TreeNode node = new TreeNode(preorder[p]);
        int k = i1;
        for (int x=i1; x<=i2; x++) {
            if (inorder[x]==preorder[p]) {
                k=x;
                break;
            }
        }
        node.left = buildTree(preorder, inorder, p+1, i1, k-1);
        node.right = buildTree(preorder, inorder, p+1, k+1, i2);
        return node;
    }
}
