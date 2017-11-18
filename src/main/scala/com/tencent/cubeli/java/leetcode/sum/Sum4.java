package com.tencent.cubeli.java.leetcode.sum;

import java.util.*;

/**
 * Created by waixingren on 18/11/2017.
 * 该代码还有需要修改的地方，即cache中得一个key可能对应多个元素的组合，所以cache的结构应该是Map<Integer, List<List<Integer></>></></>></>></>
 * 在第二个双层循环进行循环时，一定要保证获取的pair的最大的下标大于i，要不然就会重叠(为什么不用考虑大于j的情况)
 *
 * 因为有一个map缓存前两个值的结果，所以两个双层循环的复杂度是O(n^2) + O(n^2)，即O(n^2)
 */
public class Sum4 {

    public static void main(String[] args) {

        int[] source = {-1, -4, -2,1,-3,4,5};

        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> cache = new HashMap<>();
        Arrays.sort(source);
        for (int i = 0; i < source.length; i++) {

            System.out.println(source[i]);
        }

        System.out.println("begin to process");

        for (int i = 0; i < source.length; i++) {

            for(int j = i+1; j < source.length; j++){

                Integer key = new Integer(source[i] + source[j]);
                if(!cache.containsKey(key)){

                    List<Integer> l = new ArrayList<>();
                    l.add(i);
                    l.add(j);
                    cache.put(key, l);
                }
            }
        }

        for (int i = 0; i < source.length; i++) {

            for (int j = i+1; j < source.length; j++) {

                int sum = source[i] + source[j];
                Integer key = new Integer(0 - sum);
                if(!cache.containsKey(key)){
                    continue;
                }

                List<Integer> l = cache.get(key);
                for (int k = 0; k < l.size(); k++) {
                    if(i <= l.get(k)){
                        continue;
                    }
                    List<Integer> oneResult = new ArrayList<>();
                    oneResult.add(l.get(0));
                    oneResult.add(l.get(1));
                    oneResult.add(i);
                    oneResult.add(j);
                    result.add(oneResult);
                }
            }
        }

        for(List<Integer> l : result){

            System.out.println(source[l.get(0)]+source[l.get(1)]+ source[l.get(2)] + source[l.get(3)]);
        }

    }
}
