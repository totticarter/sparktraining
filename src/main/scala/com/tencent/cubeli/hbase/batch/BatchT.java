package com.tencent.cubeli.hbase.batch;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waixingren on 10/7/17.
 */
public class BatchT {

    public static void main(String[] args) {

        String tableName = "member";
        try {
            Configuration conf = HBaseConfiguration.create();
            Connection connection = ConnectionFactory.createConnection(conf);
            Table table = connection.getTable(TableName.valueOf(tableName));

            List<Row> batchOperations = new ArrayList<>();
            Put put = new Put(Bytes.toBytes("litairan"));
            put.addColumn(Bytes.toBytes("address"), Bytes.toBytes("country"), Bytes.toBytes("Japan"));
            batchOperations.add(put);

            Get get = new Get(Bytes.toBytes("cubeli"));
            batchOperations.add(get);

            Object[] results = new Object[batchOperations.size()];
            table.batch(batchOperations, results);

            for(int i = 0; i < results.length; i++){
                System.out.println(results[i]);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
