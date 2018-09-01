package com.tencent.cubeli.java.collection.queue;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by liyong on 05/08/2018.
 */
public class PriorityBlockingQueueTest {

    public static void main(String[] args) {

        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(2);

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        System.out.println(queue.size());
    }
}
