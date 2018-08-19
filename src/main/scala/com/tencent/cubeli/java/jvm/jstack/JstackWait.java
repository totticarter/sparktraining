package com.tencent.cubeli.java.jvm.jstack;

/**
 * Created by liyong on 19/08/2018.
 */
public class JstackWait {

    public void run() {
        synchronized(this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
