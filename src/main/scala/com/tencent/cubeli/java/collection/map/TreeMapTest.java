package com.tencent.cubeli.java.collection.map;

import java.util.TreeMap;

/**
 * Created by liyong on 2019/3/6.
 */
public class TreeMapTest {

    public static void main(String[] args) {

        TreeMap treeMap = new TreeMap();
        treeMap.put(2, "one");
        treeMap.put(1, "two");
        treeMap.put(3, "three");

        treeMap.forEach((key, value) -> {

            System.out.println(key);
        });

        System.out.println(treeMap.pollFirstEntry().getKey());
        System.out.println(treeMap.pollFirstEntry().getKey());

    }
}
