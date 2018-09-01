package com.tencent.cubeli.java.designpattern.listener;

/**
 * Created by liyong on 30/07/2018.
 */
public class CarListener implements ListenerInterface {
    public void handleEvent(DemoEvent de) {
        System.out.println("汽车来了,准备!");
        de.say();//回调
    }
}
