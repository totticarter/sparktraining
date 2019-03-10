package com.tencent.cubeli.java.cast;

/**
 * Created by liyong on 2019/1/30.
 */
public class Imp implements Intf {

    @Override
    public void print() {
        System.out.println("hello");
    }

    public static void main(String[] args) {

        Intf intf = new Imp();
        Imp imp = (Imp)intf;

        System.out.println(intf.getClass().getSimpleName());
        System.out.println(intf.getClass().getClassLoader().toString());
        System.out.println(imp.getClass().getClassLoader().toString());

        Imp imp1 = new Imp();
        Imp imp2 = (Imp)imp1;
    }
}
