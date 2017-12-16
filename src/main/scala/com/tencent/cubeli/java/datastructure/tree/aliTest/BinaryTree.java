package com.tencent.cubeli.java.datastructure.tree.aliTest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTree {


    public TreeNode root=null;

    public BinaryTree(){
        root=new TreeNode(1,2);
    }

    public static Map<TreeNode, List<TreeNode>> nodeToNodes = new HashMap<>();

    public void createBinTree(TreeNode root){
        TreeNode newNodeB = new TreeNode(2,4);
        TreeNode newNodeC = new TreeNode(3,3);
        TreeNode newNodeD = new TreeNode(4,5);
        TreeNode newNodeE = new TreeNode(5,2);
        TreeNode newNodeF = new TreeNode(6,1);
        root.leftChild=newNodeB;
        root.rightChild=newNodeC;
        root.leftChild.leftChild=newNodeD;
        root.leftChild.rightChild=newNodeE;
        root.rightChild.rightChild=newNodeF;
    }


    public void preOrder(TreeNode subTree){
        if(subTree!=null){
            childMatch(subTree, subTree);
            preOrder(subTree.leftChild);
            preOrder(subTree.rightChild);
        }
    }

    public void childMatch(TreeNode currentRoot, TreeNode subtree){

        List<TreeNode> nodes = nodeToNodes.get(currentRoot);
        if(nodes == null){
            nodes = new ArrayList<>();
            nodes.add(currentRoot);
        }

        int[] delta = getDelta(currentRoot);
        if(subtree.leftChild != null && subtree.data  == subtree.leftChild.data+delta[0]){
            nodes.add(subtree.leftChild);
            nodeToNodes.put(currentRoot, nodes);
            childMatch(currentRoot, subtree.leftChild);
        }
        if(subtree.rightChild != null && subtree.data +1 == subtree.rightChild.data+delta[1]){
            nodes.add(subtree.rightChild);
            nodeToNodes.put(currentRoot, nodes);
            childMatch(currentRoot, subtree.rightChild);
        }
    }

    public int[] getDelta(TreeNode currentRoot){

        int[] delta = new int[2];

        //1-2-3
        if(currentRoot.leftChild!=null
                && currentRoot.rightChild!=null
                && currentRoot.rightChild.data-1==currentRoot.data
                && currentRoot.data-1==currentRoot.leftChild.data){
            delta[0]=-1;
            delta[1]=1;

         //3-2-1
        }else if(currentRoot.leftChild!=null
                && currentRoot.rightChild!=null
                && currentRoot.rightChild.data+1==currentRoot.data
                && currentRoot.data+1==currentRoot.leftChild.data){
            delta[0]=1;
            delta[1]=-1;
        }

        return delta;
    }


    private class  TreeNode{
        private int key=0;
        public int data = 0;
        public TreeNode leftChild=null;
        public TreeNode rightChild=null;

        public TreeNode(int key,int data){
            this.key=key;
            this.data=data;
            this.leftChild=null;
            this.rightChild=null;
        }
    }


    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.createBinTree(bt.root);

        //时间复杂度O(n*logn)，空间复杂度O(n*logn)
        bt.preOrder(bt.root);
        int maxSize = 0;
        TreeNode treeNode = null;
        for(Map.Entry<TreeNode, List<TreeNode>> entry: nodeToNodes.entrySet()){

            if(maxSize < entry.getValue().size()){
                treeNode = entry.getKey();
                maxSize = entry.getValue().size();
            }
        }
        List<TreeNode> longestNodes = nodeToNodes.get(treeNode);
    }
}
