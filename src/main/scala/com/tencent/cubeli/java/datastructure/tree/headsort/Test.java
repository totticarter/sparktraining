package com.tencent.cubeli.java.datastructure.tree.headsort;

import java.util.Random;

/**
 * Created by liyong on 2019/3/6.
 */
public class Test {

    public static void main(String[] args) {


                float arr[]= new float[1000000];
                for (int i=0;i<1000000;i++){
                    Random random=new Random();
                    float v = random.nextFloat() * 50f;
                    arr[i]=v;
                }
                long start=System.currentTimeMillis();
                float[] maxNumber = HeapUtils.getMaxNumber(10, arr);
                HeapUtils.heapSort(maxNumber);
                HeapUtils.print(maxNumber);
                System.out.println(System.currentTimeMillis()-start);

        }
}
