package com.tencent.cubeli.hbase.crudnew;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by waixingren on 10/7/17.
 */
public class PutT {

    public static void main(String[] args) throws IOException{

        //居然也被弃用了，cao
//        HConnection connection = HConnectionManager.createConnection(config);
//        HTableInterface table = connection.getTable("tablename");
        String rowKey = "cubeli";
        String tableName = "member";

        String[] addressColumns = {"country", "city"};
        String[] addressValues = {"China", "ShenZhen"};

        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);


        Table table = connection.getTable(TableName.valueOf(tableName));
        HColumnDescriptor[] columnFamilies = table.getTableDescriptor().getColumnFamilies();
        Put put = new org.apache.hadoop.hbase.client.Put(Bytes.toBytes(rowKey));

        for (int i = 0; i < columnFamilies.length; i++) {
            String familyName = columnFamilies[i].getNameAsString();
            if (familyName.equals("address")) {
                for (int j = 0; j < addressColumns.length; j++) {

                    put.addColumn(familyName.getBytes(), addressColumns[j].getBytes(), addressValues[j].getBytes());
                }
            }
        }

        table.put(put);

    }
}
