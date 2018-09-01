package com.tencent.cubeli.java.designpattern.listener;

/**
 * Created by liyong on 30/07/2018.
 */
public class PlaneListener implements ListenerInterface {
    public void handleEvent(DemoEvent de) {
        System.out.println("飞机来了,准备!");
        de.say();//回调
    }
}
