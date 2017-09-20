package com.tencent.cubeli.java.collection.queue;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cubeli on 2017/9/19.
 */
@SuppressWarnings("unused")
public class PriorityTrueBlockingQueue<T> {

    private int queueCapacity = 10;
    private Queue<T> queue = new PriorityBlockingQueue<>();
    private ReentrantLock putLock = new ReentrantLock();
    private ReentrantLock takeLock = new ReentrantLock();
    private Condition notFull = putLock.newCondition();
    private Condition notEmpty = takeLock.newCondition();

    public void put(T e) throws InterruptedException{


        try{

            putLock.lockInterruptibly();
            while(queue.size() == queueCapacity){

                System.out.println("put: queue is full, wait...");
                notFull.await();
            }
            System.out.println("put: queue is not full, add element");
            this.queue.add(e);
            if(queue.size() + 1 < queueCapacity){
                notFull.signal();
            }
        }finally {
            putLock.unlock();
        }
    }

    public T poll() throws InterruptedException{

        try{
            takeLock.lock();
            while(queue.size() == 0){
                System.out.println("poll: queue size is 0, wait...");
                notEmpty.await();
            }
            System.out.println("poll: queue size is not 0, start to poll");
            if(queue.size() > 0){
                notEmpty.signal();
            }
            return queue.poll();

        }finally {
            takeLock.unlock();
        }
    }
}
