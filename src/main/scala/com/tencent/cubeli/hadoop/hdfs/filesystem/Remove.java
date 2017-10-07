package com.tencent.cubeli.hadoop.hdfs.filesystem;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

/**
 * Created by waixingren on 10/6/17.
 */
public class Remove {

    public static void main(String[] args) {

        try{

            //目标文件
            String destfile = "hdfs://localhost:9000/tmp/test/readmefromlocal-bk2.txt";

            Configuration config = new Configuration();
            // 程序配置
            config.set("fs.default.name", "hdfs://localhost:9000");

            FileSystem hdfs = FileSystem.get(new URI("hdfs://localhost:9000"), config, "waixingren");
            Path destPath = new Path(destfile);

            hdfs.delete(destPath, false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
