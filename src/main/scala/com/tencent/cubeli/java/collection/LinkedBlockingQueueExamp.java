package com.tencent.cubeli.java.collection;


import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by cubeli on 2017/9/19.
 */
@SuppressWarnings("unused")
public class LinkedBlockingQueueExamp {

    public static void main(String[] args) {

        try{

            LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(3);

             Object o = queue.poll();
            boolean flag = queue.offer("ccca");
            if(flag){
                System.out.println(flag);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
