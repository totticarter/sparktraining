package com.tencent.cubeli.java.jvm.jstack;

/**
 * Created by liyong on 19/08/2018.
 */
public class JstackDeaclock implements Runnable{

    private Object monitor_A = new Object();

    private Object monitor_B = new Object();

    public void  method_A(){
        synchronized(monitor_A) {
            synchronized(monitor_B) {
                System.out.println(Thread.currentThread().getName()+" invoke method A");
            }
        }
    }

    public void  method_B(){
        synchronized(monitor_B) {
            synchronized(monitor_A) {
                System.out.println(Thread.currentThread().getName()+" invoke method B");
            }
        }
    }

    public void run() {
        for(int i=0;i<1;i--){
            method_A();
            method_B();
        }
    }

    public static void main(String[] args) {
        JstackDeaclock t1 = new JstackDeaclock();
        Thread ta = new Thread(t1, "A");
        Thread tb = new Thread(t1, "B");

        ta.start();
        tb.start();
    }
}
