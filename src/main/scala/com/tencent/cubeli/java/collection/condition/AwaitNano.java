package com.tencent.cubeli.java.collection.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by waixingren on 9/23/17.
 */
public class AwaitNano {

    public static void main(String[] args) throws InterruptedException{

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        lock.lock();
        boolean flag = true;
        while (flag){

            long l = condition.awaitNanos(100000);
            System.out.println(l);
        }

    }
}
