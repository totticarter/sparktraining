package com.tencent.cubeli.java.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Created by liyong on 2019/2/23.
 */
public class MethodHandlerTest {

    public static void main(String[] args) {
//
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class, int.class, int.class);
        try {
            MethodHandle mh = lookup.findVirtual(String.class, "substring", type);
            String str = (String) mh.invokeExact("Hello World", 1, 3);
            System.out.println(str);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

//        test2();

    }

    public static void test2(){

        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodHandle mh = lookup.findVirtual(String.class, "indexOf", MethodType.methodType(int.class, String.class, int.class));
            mh = mh.bindTo("Hello").bindTo("l");
            System.out.println(mh.invoke(2));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
//
//        String str = "Heeeeello";
//        System.out.println(str.indexOf("l", 2));
    }
}
