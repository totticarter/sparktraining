package com.tencent.cubeli.hbase.crudold;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by waixingren on 10/7/17.
 */
public class PutT {

    public static void main(String[] args) {

        String rowKey = "mike";
        String tableName = "member";

        String[] addressColumns = {"country", "city"};
        String[] addressValues = {"China", "ShenZhen"};

        String executeMode = "put";
        Configuration conf = HBaseConfiguration.create();

        if(executeMode.equals("put")){

            org.apache.hadoop.hbase.client.Put put = new org.apache.hadoop.hbase.client.Put(Bytes.toBytes(rowKey));
            try{
                HTable table = new HTable(conf, Bytes.toBytes(tableName));
                HColumnDescriptor[] columnFamilies = table.getTableDescriptor().getColumnFamilies();

                for (int i = 0; i < columnFamilies.length; i++) {
                    String familyName = columnFamilies[i].getNameAsString();
                    if (familyName.equals("address")) {
                        for (int j = 0; j < addressColumns.length; j++) {

                            put.addColumn(familyName.getBytes(), addressColumns[j].getBytes(), addressValues[j].getBytes());
                        }
                    }
                }

                table.put(put);
            }catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("add data Success!");
        }else if(executeMode.equals("delete")){


        }

    }
}
