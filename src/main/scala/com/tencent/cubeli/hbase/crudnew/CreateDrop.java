package com.tencent.cubeli.hbase.crudnew;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by waixingren on 10/7/17.
 * https://hbase.apache.org/1.1/apidocs/org/apache/hadoop/hbase/client/HBaseAdmin.html
 */
public class CreateDrop {


    public static void main(String[] args) throws  IOException{
        String executeMode = "createtable";//droptable;createtable

        String[] families = {"member_id", "address", "info"};
        String tableName = "member";

        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));

        if(executeMode.equals("createtable")){
            for (int i = 0; i < families.length; i++) {
                desc.addFamily(new HColumnDescriptor(families[i]));
            }
            if (admin.tableExists(TableName.valueOf(tableName))) {
                System.out.println("table Exists!");
                System.exit(0);
            } else {
                admin.createTable(desc);
                System.out.println("create table Success!");
            }
        }else if(executeMode.equals("droptable")){

            try{

                admin.disableTable(TableName.valueOf(tableName));
                admin.deleteTable(TableName.valueOf(tableName));
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

}
