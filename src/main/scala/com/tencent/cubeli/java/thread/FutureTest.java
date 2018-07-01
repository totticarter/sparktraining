package com.tencent.cubeli.java.thread;

import java.util.Map;
import java.util.concurrent.*;
import java.util.Random;

/**
 * 多线程执行，异步获取结果
 *
 * @author i-clarechen
 *
 */
public class FutureTest {


    public static int queryCount = 100;
    public static int numberThread = 0;
    public int completedCount = 0;
    public double allTimes = 0.0;
    public long start = System.currentTimeMillis();
    public static void main(String[] args) {

        queryCount = Integer.parseInt(args[0]);
        numberThread = Integer.parseInt(args[1]);

        FutureTest t = new FutureTest();
        Map<Integer, Future<String>> futureMap = new ConcurrentHashMap<>();
        t.generate(16, futureMap);
        t.getResult(futureMap);
    }

    /**
     * 生成指定数量的线程，都放入future数组
     *
     * @param threadNum
     * @param fMap
     */
    public void generate(int threadNum, Map<Integer, Future<String>>  fMap) {
        ExecutorService service = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < queryCount; i++) {
            Future<String> f = service.submit(executeQuery(i));
            fMap.put(i, f);
        }
        service.shutdown();
    }


    /**
     * 从future中获取线程结果，打印结果
     *
     * @param fMap
     */
    public void getResult(Map<Integer, Future<String>>  fMap) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(getCollectJob(fMap));
        service.shutdown();
    }

    /**
     * 生成指定序号的线程对象
     *
     * @param i
     * @return
     */
    public Callable<String> executeQuery(final int i) {
        final int time = new Random().nextInt(3)+2;
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000 * time);
                return "thread-" + i;
            }
        };
    }

    /**
     * 生成结果收集线程对象
     *
     * @param fMap
     * @return
     */
    public Runnable getCollectJob(final Map<Integer, Future<String>>  fMap) {
        return new Runnable() {
            public void run() {
                while(true){

                    if(completedCount == queryCount){
                        break;
                    }
                    System.out.println("before loop, completedCount is: " + completedCount);
                    for(Map.Entry<Integer, Future<String>> entry: fMap.entrySet()){

                        Integer id = entry.getKey();
                        Future<String> future = entry.getValue();
                        if(future.isDone() && !future.isCancelled()){
                            fMap.remove(id);
                            completedCount++;
                        }
                    }
                    System.out.println("after loop, completedCount is: " + completedCount + ", fMap size is: " + fMap.size());
                    long end = System.currentTimeMillis();
                    double take = (end - start)/1000;
                    double qps = completedCount/take;
                    System.out.println("completedCount is: " + completedCount + ", take  " + take + " s, qps is: " + qps);
                    System.out.println("================================================");
                    try {

                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
    }

}
