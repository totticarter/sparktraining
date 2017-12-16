package com.tencent.cubeli.java.leetcode.binarytree;

import java.util.Stack;

/**
 * Created by waixingren on 23/11/2017.
 */
public class BinaryTree {

    public  TreeNode root = null;
    public BinaryTree(){
        root = new TreeNode(1, "A");
    }

    class TreeNode{

        private int key = -1;
        private String value = null;
        private boolean visited = false;
        public TreeNode leftChild = null;
        public TreeNode rightChild = null;

        public TreeNode(int key, String value){
            this.key = key;
            this.value = value;
        }

        public void visit(){
            if(visited == true){
                return;
            }
            visited = true;
            System.out.println(value);
        }
    }



    public void createTree(){

        TreeNode newNodeB = new TreeNode(2,"B");
        TreeNode newNodeC = new TreeNode(3,"C");
        TreeNode newNodeD = new TreeNode(4,"D");
        TreeNode newNodeE = new TreeNode(5,"E");
        TreeNode newNodeF = new TreeNode(6,"F");


        root.leftChild=newNodeB;
        root.rightChild=newNodeC;
        root.leftChild.leftChild=newNodeD;
        root.leftChild.rightChild=newNodeE;
        root.rightChild.rightChild=newNodeF;
    }

    //时间复杂度O(n)，空间复杂度O(n)，因为使用了额外的存储空间。还有一种mirror法，空间复杂度可以到O(1)
    public void preOrder(TreeNode root){

        Stack<TreeNode> visited = new Stack<>();
        TreeNode p = root;
        while(p != null){

            //父节点和左子节点入栈,同时访问父节点和左子节点
            while(p != null){

                p.visit();
                visited.push(p);
                p = p.leftChild;
            }

            //父节点和左子节点出栈，同时访问父节点的右子节点
            while(visited.size() != 0){
                p = visited.pop();
                p = p.rightChild;
                if(p != null)
                    p.visit();
            }

        }
    }

    public void inOrder(TreeNode root){

        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while(p != null || stack.size() != 0){

            //父节点和左子节点入栈,在这个过程中不能visit，如果visit，就是先访问父节点，变成前序遍历了
            while(p != null){

                stack.push(p);
                p = p.leftChild;
            }

            //访问一个父节点，并获取其右子节点，把右子节点相关的父节点和左子节点入栈
            //注意这里不能把stack都pop出来，如果都pop出来，右子节点只能返回部分节点
            //所以如果有右子树，要右子树压栈
            if(stack.size() != 0){

                p = stack.pop();
                if(p != null){
                    p.visit();
                }
                p = p.rightChild;
            }
        }
    }

    //这个后向遍历还有问题
    public void postOrder(TreeNode root){

        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while(p != null){

            //左子树入栈
            while(p.leftChild != null){

                stack.push(p);
                p = p.leftChild;
            }

            if(p != null &&(p.rightChild == null)){
                p.visit();
                p = stack.pop();
            }

            //优先处理右子树
            stack.push(p);
            p = p.rightChild;

        }
    }

    public static void main(String[] args) {


        BinaryTree bt = new BinaryTree();
        /**
         * 创建一棵二叉树
         * <pre>
         *           A
         *     B          C
         *  D     E            F
         *  </pre>
         * @param root
         * @author WWX
         */
        bt.createTree();

//        bt.preOrder(bt.root);
//        bt.inOrder(bt.root);
        bt.postOrder(bt.root);
    }
}
