package com.tencent.cubeli.java.collection.queue;

/**
 * Created by cubeli on 2017/9/20.
 */
public class Main {

    public static void main(String[] args) throws  Exception{

        PriorityTrueBlockingQueue queue = new PriorityTrueBlockingQueue();
        Thread consumer = new Thread(new Consumer(queue));
        consumer.start();

        Thread.sleep(3000);
        System.out.println("producer start...");
        Thread producer = new Thread(new Producer(queue));
        producer.start();
    }
}
