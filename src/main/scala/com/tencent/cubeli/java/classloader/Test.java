package com.tencent.cubeli.java.classloader;

import com.google.common.collect.Sets;

import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liyong on 2019/2/9.
 * https://zhuanlan.zhihu.com/p/54693308
 * 用来测试类的热加载:在启动了main之后, 修改println的内容,然后点击Build->Compile Test.java
 * 注意: 这里需要把定时时间设置为5秒,因为可能原始的2秒无法完成重新编译,导致在加载类时找不到文件
 */
public class Test {
    public void printVersion(){
        System.out.println("当前版本:4");
    }


    public static void main(String[] args) {
        //创建一个2s执行一次的定时任务
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String swapPath = MyClassLoader.class.getResource("").getPath() + "/";
                String className = "com.tencent.cubeli.java.classloader.Test";

                //每次都实例化一个ClassLoader，这里传入swap路径，和需要特殊加载的类名
                MyClassLoader myClassLoader = new MyClassLoader(swapPath, Sets.newHashSet(className));
                try {
                    //使用自定义的ClassLoader加载类，并调用printVersion方法。
                    Object o = myClassLoader.loadClass(className).newInstance();
                    o.getClass().getMethod("printVersion").invoke(o);
                } catch (InstantiationException |
                        IllegalAccessException |
                        ClassNotFoundException |
                        NoSuchMethodException |
                        InvocationTargetException ignored) {
                }
            }
        }, 0,5000);
    }
}
