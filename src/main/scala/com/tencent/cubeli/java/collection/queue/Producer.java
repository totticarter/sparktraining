package com.tencent.cubeli.java.collection.queue;

/**
 * Created by cubeli on 2017/9/20.
 */

@SuppressWarnings("unused")
public class Producer implements  Runnable{

    public Producer(PriorityTrueBlockingQueue queue){
        this.queue = queue;
    }

    private PriorityTrueBlockingQueue queue;

    @Override
    public void run() {

        try{

            for(int i = 0; i < 100; i++){
                Thread.sleep(1000);
                queue.put("hello");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
