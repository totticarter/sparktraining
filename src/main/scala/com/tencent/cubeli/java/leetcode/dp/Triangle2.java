package com.tencent.cubeli.java.leetcode.dp;

import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waixingren on 18/11/2017.
 * 部分递归，通过保存已经计算的最大值的方式来计算
 */
public class Triangle2 {


    public static int  rowNum = 5;
    public static int[][] Result = new int[100][100];
    public static List<List<Integer>> triangle = new ArrayList<>();
    public static int[][] tmpMaxSum = {{-1},{-1,-1},{-1,-1,-1},{-1,-1,-1,-1},{-1,-1,-1,-1,-1}};
    public static int sumCount = 0;

    static {

        int[] r0 = {1};
        List<Integer> l0 = Ints.asList(r0);

        int[] r1 = {1,2};
        List<Integer> l1 = Ints.asList(r1);

        int[] r2 = {1,2,3};
        List<Integer> l2 = Ints.asList(r2);

        int[] r3 = {1,2,3,4};
        List<Integer> l3 = Ints.asList(r3);

        int[] r4 = {1,2,3,4,5};
        List<Integer> l4 = Ints.asList(r4);

        triangle.add(l0);
        triangle.add(l1);
        triangle.add(l2);
        triangle.add(l3);
        triangle.add(l4);
    }
    public static void main(String[] args) {

        int sum = maxSum(0,0);
        System.out.println(sumCount + ", " + sum);

    }

    static int maxSum(int i, int j){

        sumCount++;
        if(tmpMaxSum[i][j] != -1){
            return tmpMaxSum[i][j];
        }
        if(i == rowNum - 1){
            tmpMaxSum[i][j] = triangle.get(i).get(j);
        }else{

            int x = maxSum(i+1, j);
            int y = maxSum(i+1, j+1);
            tmpMaxSum[i][j] = Math.max(x,y) + triangle.get(i).get(j);
        }
        return tmpMaxSum[i][j];
    }
}
