package com.tencent.cubeli.java.guava.future;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Executors;
//import com.google.common.util.concurrent.Setta;

/**
 * Created by liyong on 14/10/2018.
 * ListenableFuture需要通过方法的返回值来设置,从而出发回调
 * SettableFuture可以直接调用set方法来出发回调
 */
public class SettableFutureTest2 {

    public static SettableFuture<?> future2 = SettableFuture.create();
    public final static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    public static void main(String[] args) throws InterruptedException {


//        Futures.addCallback(future2, new FutureCallback<Boolean>() {
//            @Override
//            public void onSuccess(Boolean result) {
//                System.err.println("BooleanTask: " + result);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                System.err.println("exception....");
//            }
//        });

        reserveMemory();
        Thread.sleep(5000);

        if(future2.isDone()){
            System.out.printf("Before set null, SettableFuture<?> is: " + future2.isDone());
        }
        future2.set(null);
        if(future2.isDone()){
            System.out.printf("After set null, SettableFuture<?> is:" + future2.isDone());
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                future2.setException(new Exception());
            }
        }).start();

    }

    public static ListenableFuture<?> reserveMemory(){

        return future2;
    }
}
