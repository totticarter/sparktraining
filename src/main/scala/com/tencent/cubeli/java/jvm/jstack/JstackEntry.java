package com.tencent.cubeli.java.jvm.jstack;

/**
 * Created by liyong on 18/08/2018.
 */
public class JstackEntry implements Runnable{

    public void run() {
        synchronized(this) {
            for (int i = 0; i < 1; i--) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
    }
    public static void main(String[] args) {
        JstackEntry t1 = new JstackEntry();
        Thread ta = new Thread(t1, "A");
        Thread tb = new Thread(t1, "B");
        ta.start();
        tb.start();
    }

}
