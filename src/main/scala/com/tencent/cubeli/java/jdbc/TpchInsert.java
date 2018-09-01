package com.tencent.cubeli.java.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * Created by liyong on 02/08/2018.
 */
public class TpchInsert {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        int insertCount = Integer.parseInt(args[0]);
        String tableName = args[1];
        String hostPort = args[2];
        String filePath = args[3];
        int count = 0;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + hostPort+ "/liyong_test_db", "root", "root");

            String sql = "insert into " +tableName +  " (l_orderkey,l_partkey,l_suppkey,l_linenumber,l_quantity,l_extendedprice,l_discount,l_tax,l_returnflag,l_linestatus,l_shipdate,l_commitdate,l_receiptdate,l_shipinstruct,l_shipmode,l_comment) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            try {
                File file = new File(filePath);
                if (file.isFile() && file.exists()) {
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while ((lineTxt = bufferedReader.readLine()) != null) {

                        String[] values = lineTxt.split("//|");
                        System.out.println("values size is: " + values.length);
                        ps.setLong(1,Long.valueOf(values[0]));
                        ps.setInt(2, Integer.valueOf(values[1]));
                        ps.setInt(3, Integer.valueOf(values[2]));
                        ps.setInt(4, Integer.valueOf(values[3]));
                        ps.setFloat(5, Float.valueOf(values[4]));
                        ps.setFloat(6, Float.valueOf(values[5]));
                        ps.setFloat(7, Float.valueOf(values[6]));
                        ps.setString(8, values[7]);
                        ps.setString(9, values[8]);
                        ps.setDate(10, java.sql.Date.valueOf(values[9]));
                        ps.setDate(11, java.sql.Date.valueOf(values[10]));
                        ps.setDate(12, java.sql.Date.valueOf(values[11]));
                        ps.setString(13, values[12]);
                        ps.setString(14, values[13]);
                        ps.setString(15, values[14]);
                        ps.setString(16, values[15]);

                        ps.executeUpdate(sql);
                    }
                    read.close();
                } else {
                    System.out.println("找不到指定的文件");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
