package com.tencent.cubeli.java.leetcode.binarytree.bst;

/**
 * Created by waixingren on 23/11/2017.
 */
public class KMin {

    public static void main(String[] args) {


        BinarySearchTree binarySearchTree = new BinarySearchTree();
        BinarySearchTree.TreeNode node1 = new BinarySearchTree.TreeNode(8);
        BinarySearchTree.TreeNode node2 = new BinarySearchTree.TreeNode(1);
        BinarySearchTree.TreeNode node3 = new BinarySearchTree.TreeNode(10);
        BinarySearchTree.TreeNode node4 = new BinarySearchTree.TreeNode(3);
        BinarySearchTree.TreeNode node5 = new BinarySearchTree.TreeNode(9);
        BinarySearchTree.TreeNode node6 = new BinarySearchTree.TreeNode(18);
        BinarySearchTree.TreeNode node7 = new BinarySearchTree.TreeNode(20);

        binarySearchTree.root = binarySearchTree.insertRec(binarySearchTree.root, node1);
        binarySearchTree.root = binarySearchTree.insertRec(binarySearchTree.root, node2);
        binarySearchTree.root = binarySearchTree.insertRec(binarySearchTree.root, node3);
        binarySearchTree.root = binarySearchTree.insertRec(binarySearchTree.root, node4);
        binarySearchTree.root = binarySearchTree.insertRec(binarySearchTree.root, node5);
        binarySearchTree.root = binarySearchTree.insertRec(binarySearchTree.root, node6);
        binarySearchTree.root = binarySearchTree.insertRec(binarySearchTree.root, node7);

        binarySearchTree.inOrder(binarySearchTree.root);
    }
}
