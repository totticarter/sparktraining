package com.tencent.cubeli.java.invoke;


/**
 * Created by liyong on 2019/2/23.
 */



public class Lamda {


    public void lambda(Func func) {
        return;
    }

    public static void main(String[] args) {
        Lamda lambda = new Lamda();
        lambda.lambda(s -> {
            return true;
        });
        lambda.lambda(s -> {
            return true;
        });

    }
}
