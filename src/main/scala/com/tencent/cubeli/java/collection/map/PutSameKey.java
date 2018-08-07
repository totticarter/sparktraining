package com.tencent.cubeli.java.collection.map;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * Created by liyong on 05/08/2018.
 */
public class PutSameKey {

    public static void main(String[] args) {

        Map<String, Integer> map = new HashedMap();
        map.put("one", 1);
        Integer v = map.put("one", 2);
        System.out.println(v);
    }
}
