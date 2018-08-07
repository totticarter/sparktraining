package com.tencent.cubeli.java.collection.queue;

/**
 * Created by cubeli on 2017/9/19.
 */
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityQueueExample {

    static class Customer {

        private int id;
        private String name;

    public Customer(int i, String n){
            this.id=i;
            this.name=n;
        }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}


    public static void main(String[] args) {

        blockQueueTest();

        Queue<Integer> integerPriorityQueue = new PriorityQueue<>(5);
        Random rand = new Random();
        int e = 0;
        for(int i=0;i<7;i++){
            e = new Integer(rand.nextInt(100));
            System.out.println("enqueue " + e);
            System.out.println(integerPriorityQueue.add(e) + " e " + e + " successed!");
        }


//        integerPriorityQueue.remove(e);
//        System.out.println("after remove "  + e + ", size is: " + integerPriorityQueue.size());
//
        for(int i=0;i<7;i++){
            Integer in = integerPriorityQueue.poll();
            System.out.println("Processing Integer:"+in + ", queue size is: " + integerPriorityQueue.size());
        }
//
//        Queue<Customer> customerPriorityQueue = new PriorityQueue<>(7, idComparator);
//        addDataToQueue(customerPriorityQueue);
//
//        pollDataFromQueue(customerPriorityQueue);

    }

    public static Comparator<Customer> idComparator = new Comparator<Customer>(){

        @Override
        public int compare(Customer c1, Customer c2) {
            return c1.getId() - c2.getId();
        }
    };

    private static void addDataToQueue(Queue<Customer> customerPriorityQueue) {
        Random rand = new Random();
        for(int i=0; i<7; i++){
            int id = rand.nextInt(100);
            customerPriorityQueue.add(new Customer(id, "Pankaj "+id));
        }
    }

    private static void pollDataFromQueue(Queue<Customer> customerPriorityQueue) {
        while(true){
            Customer cust = customerPriorityQueue.poll();
            if(cust == null) break;
            System.out.println("Processing Customer with ID="+cust.getId());
        }
    }

    private static void blockQueueTest(){

        Queue<Integer> queue = new PriorityBlockingQueue<>(3);
        queue.add(1);
        queue.add(1);
        queue.add(1);
        queue.add(1);
        boolean flag = queue.offer(1);
        if(flag){
            System.out.println(flag);
        }
    }

}
