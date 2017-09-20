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

            for(int i = 0; i < 100; i++){
                Thread.sleep(100);
                int value = random.nextInt(100);
                System.out.println("put " + value + " to queue");
                queue.put(value);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
