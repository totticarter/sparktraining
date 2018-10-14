package com.tencent.cubeli.java.guava.future;

import com.google.common.util.concurrent.*;
import scala.concurrent.impl.CallbackRunnable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
//import com.google.common.util.concurrent.Setta;

/**
 * Created by liyong on 14/10/2018.
 * ListenableFuture需要通过方法的返回值来设置,从而出发回调
 * SettableFuture可以直接调用set方法来出发回调
 */
public class SettableFutureTest {

    public static SettableFuture<Boolean> future2 = SettableFuture.create();
    public final static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    public static void main(String[] args) throws InterruptedException {


        Futures.addCallback(future2, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                System.err.println("BooleanTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.err.println("exception....");
            }
        });

        Thread.sleep(2000);

        future2.set(true);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                future2.setException(new Exception());
//            }
//        }).start();

    }
}
