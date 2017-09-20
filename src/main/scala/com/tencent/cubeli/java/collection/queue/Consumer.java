package com.tencent.cubeli.java.collection.queue;

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

            for(int i = 0; i < 100; i++){
                Thread.sleep(1000);
                int value = (int)queue.poll();
                System.out.println("poll " + value);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
