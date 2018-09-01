package com.tencent.cubeli.java.thread;

import java.util.Scanner;

/**
 * Created by liyong on 04/08/2018.
 */
public class VolatileTest implements Runnable{
    private static volatile boolean flag = true ;
    @Override
    public void run() {
        while (flag){
        }
        System.out.println("thread A exit at: " + System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName() +"执行完毕");
    }
    public static void main(String[] args) throws InterruptedException {
        VolatileTest aVolatile = new VolatileTest();
        new Thread(aVolatile,"thread A").start();
        System.out.println("main 线程正在运行") ;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String value = sc.next();
            if(value.equals("1")){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        aVolatile.stopThread();
                    }
                }).start();
                break ;
            }
        }
        System.out.println("主线程退出了！");
    }
    private void stopThread(){
        System.out.println("set flag to false at: " + System.currentTimeMillis());
        flag = false ;
    }
}
