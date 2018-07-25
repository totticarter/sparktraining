package com.tencent.cubeli.java.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyong on 25/07/2018.
 */
public class JConsoleUnsafeTest {

    public static void main(String[] args) {

        try {
            fillHeap(1000);
            System.gc();
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class OOMObject{

        public byte[] placeHolder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException{

        List<OOMObject> list = new ArrayList<>();
        for(int i = 0; i < num; i++){

            Thread.sleep(50);
            System.out.println("count is: " + i);
            list.add(new OOMObject());
        }

    }
}
