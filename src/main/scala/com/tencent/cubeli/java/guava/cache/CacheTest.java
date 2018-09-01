package com.tencent.cubeli.java.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyong on 08/08/2018.
 */
public class CacheTest {


    public static void main(String[] args) {

        LoadingCache<Key, Graph> graphs = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .removalListener(MY_LISTENER)
                .build(
                        new CacheLoader<Key, Graph>() {
                            public Graph load(Key key) throws Exception {
                                return createExpensiveGraph(key);
                            }
                        });

        try{

            graphs.put(new Key("one"), new Graph());
            graphs.get(new Key("two"));
            graphs.put(new Key("one"), new Graph());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Graph createExpensiveGraph(Key key){

        return new Graph();
    }
}
