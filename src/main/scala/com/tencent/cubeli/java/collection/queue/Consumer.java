package com.tencent.cubeli.java.collection.queue;

import java.util.Queue;

/**
 * Created by cubeli on 2017/9/20.
 */
@SuppressWarnings("unused")
public class Consumer implements  Runnable{

    public Consumer(PriorityTrueBlockingQueue queue){
        this.queue = queue;
    }

    private PriorityTrueBlockingQueue queue;
    @Override
    public void run() {

        try{

            for(int i = 0; i < 10; i++){
                Thread.sleep(1000);
                queue.poll();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
