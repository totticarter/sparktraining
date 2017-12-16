package com.tencent.cubeli.java.leetcode.binarytree.bst;

/**
 * Created by waixingren on 05/12/2017.
 * 我的思路，对BST做中序遍历，然后判断
 */
public class ValidBST {


    public static void main(String[] args) {

        BinarySearchTree binarySearchTree = BinarySearchTree.createBST();
        binarySearchTree.inOrder(binarySearchTree.root);



    }
}
