package com.tencent.cubeli.hbase.crudnew;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by waixingren on 10/7/17.
 */
public class DeleteT {

    public static void main(String[] args) {
        String rowKey = "cubeli";
        String tableName = "member";

        String[] addressColumns = {"country", "city"};
        String[] addressValues = {"China", "ShenZhen"};

        try{
            Configuration conf = HBaseConfiguration.create();
            Connection connection = ConnectionFactory.createConnection(conf);
            Table table = connection.getTable(TableName.valueOf(tableName));

            Delete delete = new Delete(Bytes.toBytes(rowKey));
            String address = "address";
            String country = "country";
//            delete.addFamily(address.getBytes());
            delete.addColumn(address.getBytes(), country.getBytes());
            table.delete(delete);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
