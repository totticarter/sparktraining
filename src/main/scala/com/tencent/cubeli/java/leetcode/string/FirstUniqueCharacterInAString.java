package com.tencent.cubeli.java.leetcode.string;

/**
 * Created by waixingren on 18/11/2017.
 * 这个是什么原理，没看明白
 * 我的思路，双层循环，固定一个，循环剩余的，如果能找到相同的，则跳出。复杂度O(n^2)
 */
public class FirstUniqueCharacterInAString {

    int[] CHAR = new int[256];
    public static void main(String[] args) {

        System.out.println(new FirstUniqueCharacterInAString().firstUniqChar("loveleetcode"));
    }

    public int firstUniqChar(String s) {
        if (s == null || s.isEmpty()) return -1;

        for (int i = 0, l = s.length(); i < l; i++)
            CHAR[s.charAt(i)]++;

        for (int i = 0, l = s.length(); i < l; i++) {
            if (CHAR[s.charAt(i)] == 1)
                return i;
        }

        return -1;
    }
}
