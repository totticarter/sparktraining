package com.tencent.cubeli.java.collection.queue;
import java.util.Random;
/**
 * Created by cubeli on 2017/9/20.
 */

@SuppressWarnings("unused")
public class Producer implements  Runnable{

    private static Random random = new Random();

    public Producer(PriorityTrueBlockingQueue queue){
        this.queue = queue;
    }

    private PriorityTrueBlockingQueue queue;

    @Override
    public void run() {

        try{

            for(int i = 0; i < 1000; i++){
                Thread.sleep(10);
                int id = random.nextInt(100);
                String name = "userid" + String.valueOf(id);
                System.out.println("put " + id);
                PriorityQueueExample.Customer customer = new PriorityQueueExample.Customer(id, name);
                queue.put(customer);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
