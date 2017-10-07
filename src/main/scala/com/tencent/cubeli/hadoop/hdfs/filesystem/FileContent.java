package com.tencent.cubeli.hadoop.hdfs.filesystem;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;

import java.net.URI;

/**
 * Created by waixingren on 10/6/17.
 * 演示truncate和append api的使用
 *
 * truncate 需要2.7.3版本才能支持，如果把pom文件里的依赖改成2.7.3，会和其他的依赖冲突，报找不到类的异常
 *
 * 对于append的测试，会报各种异常，例如因为版本过旧导致不存在storageid，或者集群处于lease recovery模式等
 * 如果在本地生成一个空文件，put到hdfs上，然后append，不会报错，通过cat可以看到文件内容，但是如果再次append
 * 这个文件，要么无法append成功，要么抛上边的异常
 *
 * 更换了2.7.3的依赖之后，已经可以进行truncate了
 */
public class FileContent {

    public static void main(String[] args) {

        try{

            //目标文件
            String destfile = "hdfs://localhost:9000/tmp/test/readmefromlocal.txt";

            Configuration config = new Configuration();
            // 程序配置
            config.set("fs.default.name", "hdfs://localhost:9000");
            config.set("dfs.support.append","true");

            FileSystem hdfs = FileSystem.get(new URI("hdfs://localhost:9000"), config, "waixingren");
            Path destPath = new Path(destfile);

            DistributedFileSystem dfs = (DistributedFileSystem)hdfs;
            dfs.truncate(destPath,10);

//            FSDataOutputStream fo = hdfs.append(destPath);
//            String test = "hello world hello world hello world hello world hello world hello world hello world hello world hello world hello world hello world ";
//            fo.write(test.getBytes());
//            hdfs.close();

        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
