


package com.tencent.cubeli.java;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * Created by waixingren on 8/26/17.
 */
public class ConcatWithStart extends UDF{


    public String evaluate(String str) {
        try {
            return "HelloWorld " + str;
        } catch (Exception e) {
            return null;
        }
    }

}