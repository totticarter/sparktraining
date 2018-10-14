package com.tencent.cubeli.java.guava.future;

/**
 * Created by liyong on 14/10/2018.
 * https://blog.csdn.net/Androidlushangderen/article/details/80372711
 *
 * xihe: NettyCommandWorker.java
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class CompletableFutureTest {
    //线程池中线程个数
    private static final int POOL_SIZE = 50;
    //带有回调机制的线程池
    private static final ListeningExecutorService service = MoreExecutors
            .listeningDecorator(Executors.newFixedThreadPool(POOL_SIZE));


    public void testListenableFuture() {
        //...
    }

    public static void main(String[] args) {

        // case1: supplyAsync
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Run supplyAsync.");
            return "Return result of Supply Async";
        });

        // case2: thenRun，与supplyAsync同线程
        future.thenRun(new Runnable() {

            @Override
            public void run() {
                System.out.println("Run action.");
            }
        });

        // case2: thenRunAsync，另启动线程执行
        future.thenRunAsync(new Runnable() {

            @Override
            public void run() {
                System.out.println("Run async action.");
            }
        });

        // 主动触发Complete结束方法
        // future.complete("Manual complete value.");
        future.whenComplete((v, e) -> {
            System.out.println("WhenComplete value: " + v);
            System.out.println("WhenComplete exception: " + e);
        });
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("Return result of Run Async.");
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            return "hello";
        });
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            return "world";
        });
        CompletableFuture<String> f = future3.thenCombine(future4,
                (x, y) -> x + "-" + y);
    }
}
