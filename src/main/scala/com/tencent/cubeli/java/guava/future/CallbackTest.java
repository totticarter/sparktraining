package com.tencent.cubeli.java.guava.future;

import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Created by liyong on 07/10/2018.
 */
public class CallbackTest {

//    @Test
    public static void FurtureTest() throws Exception {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        ListenableFuture future1 = service.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                System.out.println("start to do something for future1");
                Thread.sleep(3000);
                System.out.println("call future 1.");
                return 1;
            }
        });

        ListenableFuture future2 = service.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                Thread.sleep(1000);
                System.out.println("call future 2.");
                //       throw new RuntimeException("----call future 2.");
                return 2;
            }
        });

        final ListenableFuture allFutures = Futures.allAsList(future1, future2);

        final ListenableFuture transform = Futures.transform(allFutures, new AsyncFunction<List<Integer>, Boolean>() {
            @Override
            public ListenableFuture apply(List<Integer> results) throws Exception {
                return Futures.immediateFuture(String.format("success future:%d", results.size()));
            }
        });

        Futures.addCallback(transform, new FutureCallback<Object>() {

            public void onSuccess(Object result) {
                System.out.println(result.getClass());
                System.out.printf("success with: %s%n", result);
            }

            public void onFailure(Throwable thrown) {
                System.out.printf("onFailure%s%n", thrown.getMessage());
            }
        });

//        System.out.println(transform.get());
    }

    public static void main(String[] args) throws Exception {

        FurtureTest();
    }

}
