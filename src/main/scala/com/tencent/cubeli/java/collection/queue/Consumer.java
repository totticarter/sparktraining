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

            for(int i = 0; i < 1000; i++){
                Thread.sleep(100);
                PriorityQueueExample.Customer customer = (PriorityQueueExample.Customer)queue.poll();
                System.out.println("poll " + customer.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
