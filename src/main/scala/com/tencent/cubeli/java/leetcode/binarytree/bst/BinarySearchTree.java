package com.tencent.cubeli.java.leetcode.binarytree.bst;

/**
 * Created by waixingren on 23/11/2017.
 * 寻找bst中第k小的数。bst的父节点大于所有左子树节点，小于所有右子树节点。所以对bst做中序遍历，即可实现排序。
 */
public class BinarySearchTree {

    public TreeNode root = null;

    static class TreeNode {

        public int key = -1;
        public String value = null;
        public TreeNode left = null;
        public TreeNode right = null;
        public boolean isVisited = false;

        public TreeNode(int key){
            this.key = key;
        }

        public void visit(){
            System.out.println(key);
        }
    }

    //中序遍历
    public void inOrder(TreeNode subTree){
        if(subTree!=null){
            inOrder(subTree.left);
            visted(subTree);
            inOrder(subTree.right);
        }
    }

    public void visted(TreeNode subTree){
        subTree.isVisited=true;
        System.out.println("key:"+subTree.key+"--name:"+subTree.value);
    }

    public void valid(TreeNode subTree){

    }


    //递归插入。还有非递归插入法：
    //http://blog.csdn.net/sheepmu/article/details/38407221
    public TreeNode insertRec(TreeNode root, TreeNode x) {
        if (root == null)
            root = x;
        else if (x.key < root.key)
            root.left = insertRec(root.left, x);
        else if (x.key > root.key)
            root.right = insertRec(root.right, x);
        return root;
    }

    public static int count = 0;
//    public void inOrder(TreeNode root){
//
//        if(count++ == 4 && root != null){
//            System.out.println("key is: " + root.key);
//        }
//        if(root != null){
//
//            inOrder(root.left);
//            root.visit();
//            inOrder(root.right);
//
//        }
//    }


    public static BinarySearchTree createBST(){

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
        return binarySearchTree;
    }
}
