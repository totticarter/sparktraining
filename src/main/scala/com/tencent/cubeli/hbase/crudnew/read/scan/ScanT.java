package com.tencent.cubeli.hbase.crudnew.read.scan;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by waixingren on 10/8/17.
 */
public class ScanT {

    public static void main(String[] args) {

        String tableName = "member";
        try {
            Configuration conf = HBaseConfiguration.create();
            Connection connection = ConnectionFactory.createConnection(conf);
            Table table = connection.getTable(TableName.valueOf(tableName));

            ResultScanner resultScanner = table.getScanner(Bytes.toBytes("address"));
            System.out.println(resultScanner.next());

        }catch (IOException e){

        }
    }
}
