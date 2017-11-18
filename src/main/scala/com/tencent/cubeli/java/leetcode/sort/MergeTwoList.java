package com.tencent.cubeli.java.leetcode.sort;

import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waixingren on 18/11/2017.
 * 这个实现有问题，题目的意思是原来list的node要保留，不能生成新的
 */
public class MergeTwoList {

    public static void main(String[] args) {

        int[] a = {1,3,5,6,8,9};
        int[] b = {2,3,5,7,10,11,13};

        List<Integer> al = Ints.asList(a);
        List<Integer> bl = Ints.asList(b);
        List<Integer> newl = new ArrayList<>();

        int alidx = al.size() - 1;
        int blidx = bl.size() - 1;
        int newlIdx = al.size() + bl.size() -1;

        while(alidx>=0 && blidx >= 0){

            if(al.get(alidx) >= bl.get(blidx)){

                al.add(al.get(alidx--), newlIdx--);
            }else{
                al.add(bl.get(blidx--), newlIdx--);
            }
        }

        System.out.println("aaa");

    }
}
