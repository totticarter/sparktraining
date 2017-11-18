package com.tencent.cubeli.java.leetcode.sort;

/**
 * Created by waixingren on 18/11/2017.
 */
public class MergeTwoArray {

    public static void main(String[] args) {

        int[] aa = {1,3,5,8};
        int[] al = new int[100];
        for(int i = 0; i < aa.length; i++){
            al[i]  = aa[i];
        }

        int[] ba = {1,2,2,2,3,4,5,6,7,9};

        System.out.println(al.length);

        int ia = aa.length-1;
        int ib = ba.length-1;
        int icur = aa.length+ba.length -1;

        while(ia>=0 && ib>=0){

//            al[icur--] = al[ia] >= ba[ib] ? al[ia--] : ba[ib--];
            if(al[ia] >= ba[ib]){

                al[icur--] = al[ia--];
            }else{

                al[icur--] = ba[ib--];
            }
        }

        al[icur] = ba[ib];




    }
}
