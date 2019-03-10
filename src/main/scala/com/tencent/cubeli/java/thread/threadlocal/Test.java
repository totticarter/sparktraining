package com.tencent.cubeli.java.thread.threadlocal;

/**
 * Created by liyong on 2018/12/22.
 * https://www.cnblogs.com/dolphin0520/p/3920407.html
 * ThreadLocal测试: 如果有一个共享的变量,需要加锁才能访问;如果不使用共享变量,那么需要在每个方法中创建栈变量
 * ThreadLocal可以实现一个线程一个变量副本,即不需要加锁访问,也不需要每个方法中都创建新的变量
 */
public class Test {

    //看似是共享的变量
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();


    //只要每个线程set一下,就是这个线程自己的变量了,所以说ThreadLocal是变量的副本是不准确的,因为每个线程的ThreadLocal值可以不同
    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final Test test = new Test();
        long mainId = Thread.currentThread().getId();


        test.set();
        System.out.println("main id: " + test.getLong());
        System.out.println("main string: " + test.getString());


        Thread thread1 = new Thread(){
            public void run() {
                test.set();
                System.out.println("child id: " + test.getLong());
                System.out.println("child string: " + test.getString());
            }
        };
        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
