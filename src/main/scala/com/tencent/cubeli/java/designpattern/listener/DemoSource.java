package com.tencent.cubeli.java.designpattern.listener;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by liyong on 30/07/2018.
 */
public class DemoSource {
    private Vector repository = new Vector();//监听自己的监听器队列
    public DemoSource(){}
    public void addDemoListener(ListenerInterface dl) {
        repository.addElement(dl);
    }
    public void notifyDemoEvent() {//通知所有的监听器
        Enumeration enu = repository.elements();
        while(enu.hasMoreElements()) {
            ListenerInterface dl = (ListenerInterface)enu.nextElement();
            dl.handleEvent(new DemoEvent(this));
        }
    }
}
