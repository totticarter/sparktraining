package com.tencent.cubeli.java.collection.queue;

/**
 * Created by cubeli on 2017/9/20.
 */
public class Main {

    public static void main(String[] args) throws  Exception{

        PriorityTrueBlockingQueue queue = new PriorityTrueBlockingQueue();
        Thread.sleep(3000);
        System.out.println("producer start...");
        Thread producer1 = new Thread(new Producer(queue));
        producer1.start();
        Thread producer2 = new Thread(new Producer(queue));
        producer2.start();
        Thread producer3 = new Thread(new Producer(queue));
        producer3.start();

        System.out.println("consumer start...");
        Thread consumer = new Thread(new Consumer(queue));
        consumer.start();

    }
}
