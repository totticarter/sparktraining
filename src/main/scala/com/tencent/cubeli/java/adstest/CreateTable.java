package com.tencent.cubeli.java.adstest;

/**
 * Created by liyong on 2018/12/1.
 */
public class CreateTable {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder("CREATE EXTERNAL TABLE TXT_ORDERS(");
        sb.append("o_trade_id bigint, o_user_id bigint, " +
                "o_seller_id bigint, o_trade_time timestamp, o_iterm_id bigint, o_trade_prize double, o_bool boolean, o_int integer, o_double double,");
        for(int i = 1; i < 51; i++){
            if(i==50){
                sb.append("o_tag").append(String.valueOf(i)).append(" string) ROW FORMAT DELIMITED\n" +
                        "  FIELDS TERMINATED BY '|'\n" +
                        "STORED AS INPUTFORMAT\n" +
                        "  'org.apache.hadoop.mapred.TextInputFormat'\n" +
                        "OUTPUTFORMAT\n" +
                        "  'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat'\n" +
                        "LOCATION\n" +
                        "  'hdfs://h16c04300.na62:9000/adstest/ads_100g_orders'\n" +
                        "TBLPROPERTIES (\n" +
                        "  'COLUMN_STATS_ACCURATE'='true',\n" +
                        "  'numFiles'='0',\n" +
                        "  'numRows'='0',\n" +
                        "  'rawDataSize'='0',\n" +
                        "  'totalSize'='0',\n" +
                        "  'transient_lastDdlTime'='1477026004')");
            }
            sb.append("o_tag").append(String.valueOf(i)).append(" string, \n");
        }
        System.out.println(sb.toString());
        //insert into table users select * from txt_users;

    }
}
