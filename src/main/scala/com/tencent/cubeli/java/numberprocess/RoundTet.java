package com.tencent.cubeli.java.numberprocess;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by liyong on 02/07/2018.
 */
public class RoundTet {

    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());

//        double d = 3.14;
//        double ceil = Math.floor(0.14);
//        if(ceil == 0.0){
//
//            System.out.println(ceil);
//        }

//        BigDecimal b = new BigDecimal(d);
//        double r1 = b.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
//
//
//
        double v = Math.floor(0.11);
        double fv = Math.floor(-0.11);
        System.out.println(v);
        System.out.println(fv);

        double d1 = 1.145;
        double d2 = 1.345;
        DecimalFormat df = new DecimalFormat("###,###.00");
        df.setRoundingMode(RoundingMode.HALF_UP);

        System.out.println(df.format(d1));
        System.out.println(df.format(d2));

        double r1 = Double.valueOf(df.format(d1));
        double r2 = Double.valueOf(df.format(d2));
        System.out.println(r1);
        System.out.println(r2);


        if(Double.isInfinite(100.0/0)){

            System.out.println("aaa");
        }
//
//
//        float a = 1.30f;
//        System.out.println("a: " + a);
//        double r = roundTest(1.3456, 2);
//        System.out.println(r);
//        r = roundTest(1.3, 2);
//        System.out.println(r);
//
//        System.out.println("=============roundTest2============");
//        roundTest2(1.3, 2);

    }

    public static double roundTest(double num, long decimals){


        if (Double.isNaN(num) || Double.isInfinite(num)) {
            return num;
        }

        double factor = Math.pow(10, decimals);
        if (num < 0) {
            return -(Math.round(-num * factor) / factor);
        }

        return Math.round(num * factor) / factor;
    }

    public static void roundTest2(double num, long decimals){

        BigDecimal decimal = new BigDecimal("1.3");
        BigDecimal setScale = decimal.setScale(2,BigDecimal.ROUND_HALF_DOWN);
        System.out.println(setScale.doubleValue());
    }
}
