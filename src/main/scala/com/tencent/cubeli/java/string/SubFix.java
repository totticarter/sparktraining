package com.tencent.cubeli.java.string;

/**
 * Created by liyong on 16/08/2018.
 */
public class SubFix {

    public static void main(String[] args) {

        String a = "20180816_150724_00001_6wa4v.1.0";
        String[] strs = a.split("\\.");
        System.out.println(strs[1]);

    }
}
