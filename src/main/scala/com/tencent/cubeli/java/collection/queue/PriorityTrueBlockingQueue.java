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
                notFull.await();//在"没满"这个条件上等待，等到"没满"这个条件满足。有两个地方会满足这个条件，一是在本方法中，
                                //等待其他的producer线程通知；二是在poll方法中，等待consumer线程消费掉一个队列元素，会
                                //使队列元素数降下来
            }
            System.out.println("put: queue is not full, add element");
            this.queue.add(e);
            System.out.println("put: after add e, queue size is: " + queue.size());
            if(queue.size() + 1 < queueCapacity){
                System.out.println("put: queue size add 1 smaller than capacity, begin to signal");
                notFull.signal();
            }
        }finally {
            putLock.unlock();
        }
    }

    public T poll() throws InterruptedException{

        try{
            T e = null;
            takeLock.lock();
            if(queue.size() > 0){
                System.out.println("poll: queue size bigger than 0, begin to poll");
                e = queue.poll();
                System.out.println("poll: after poll, queue size is: " + queue.size());
                if(queue.size() > 0){
                    notEmpty.signal();//其实这里可以不需要signal，因为如果队列元素为0，返回null即可
                }
                if(queue.size() < queueCapacity){//队列"没满"，满足put方法中的等待条件，所以要signal
                    System.out.println("poll: queue size equal capacity, signal to put");
                    putLock.lock();
                    notFull.signal();
                }
            }
            return e;

        }finally {
            putLock.unlock();
            takeLock.unlock();
        }
    }
}
