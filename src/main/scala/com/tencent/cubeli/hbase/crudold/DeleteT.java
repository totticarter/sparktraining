package com.tencent.cubeli.hbase.crudold;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by waixingren on 10/7/17.
 */
public class DeleteT {

    public static void main(String[] args) {

        String rowKey = "mike";
        String tableName = "member";

        String[] addressColumns = {"country", "city"};
        String[] addressValues = {"China", "ShenZhen"};

        String executeMode = "put";
        Configuration conf = HBaseConfiguration.create();


        try{
            HTable table = new HTable(conf, Bytes.toBytes(tableName));
            HColumnDescriptor[] columnFamilies = table.getTableDescriptor().getColumnFamilies();
            Delete delete = new Delete(Bytes.toBytes(rowKey));

        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("add data Success!");
    }
}
