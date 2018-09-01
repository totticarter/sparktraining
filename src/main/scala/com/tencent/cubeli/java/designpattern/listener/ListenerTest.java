package com.tencent.cubeli.java.designpattern.listener;

public class ListenerTest {
    DemoSource ds;
    public ListenerTest(){
        try{
            ds = new DemoSource();
            //将监听器在事件源对象中登记：
            CarListener carListener = new CarListener();
            PlaneListener planeListener = new PlaneListener();
            ds.addDemoListener(carListener);
            ds.addDemoListener(planeListener);
            ds.addDemoListener(new ListenerInterface() {
                public void handleEvent(DemoEvent event) {
                    System.out.println("Method come from 匿名类...");
                }
            });
            ds.notifyDemoEvent();//触发事件、通知监听器
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new ListenerTest();
    }
}
