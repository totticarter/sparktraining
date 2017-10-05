package com.tencent.cubeli.hadoop.hdfs.filesystem;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import java.net.URI;

/**
 * Created by waixingren on 10/2/17.
 * 演示从本地文件系统拷贝到hdfs
 */
public class Copy {


    public static void main(String[] args) {

        String copyMode = "copy";//copyfromlocal;copytolocal;copy

        FileSystem hdfs = null;
        if(copyMode.equals("copyfromlocal")){

            try {
                //目标文件
                String destfile = "/tmp/test/readmefromlocal.txt";
                //源文件
                String fsrc = "/Users/waixingren/software/hadoop/hadoop-2.7.3-src/README.txt";

                Configuration config = new Configuration();
                // 程序配置
                config.set("fs.default.name", "hdfs://localhost:9000");

                hdfs = FileSystem.get(new URI("hdfs://localhost:9000"), config, "waixingren");
                Path srcPath = new Path(fsrc);
                Path destPath = new Path(destfile);

                // hdfs.copyFromLocalFile(srcPath, destPath);
                // delSrc whether to delete the src
                boolean delSrc = false;
                hdfs.copyFromLocalFile(delSrc, srcPath, destPath);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(copyMode.equals("copytolocal")){

            try {
                //目标文件
                String destfile = "hdfs://localhost:9000/tmp/test/readmefromlocal.txt";
                //源文件
                String fsrc = "/Users/waixingren/software/hadoop/hadoop-2.7.3-src/readmefromlocal-bk.txt";

                Configuration config = new Configuration();
                // 程序配置
                config.set("fs.default.name", "hdfs://localhost:9000");

                hdfs = FileSystem.get(new URI("hdfs://localhost:9000"), config, "waixingren");
                Path srcPath = new Path(fsrc);
                Path destPath = new Path(destfile);

                hdfs.copyToLocalFile(destPath,srcPath);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(copyMode.equals("copy")){

            try{

                //目标文件
                String destfile = "hdfs://localhost:9000/tmp/test/readmefromlocal.txt";
                //源文件
                String fsrc = "hdfs://localhost:9000/tmp/test/readmefromlocal-bk.txt";

                Configuration config = new Configuration();
                // 程序配置
                config.set("fs.default.name", "hdfs://localhost:9000");

                hdfs = FileSystem.get(new URI("hdfs://localhost:9000"), config, "waixingren");
                Path srcPath = new Path(fsrc);
                Path destPath = new Path(destfile);

                FileUtil.copy(hdfs,destPath, hdfs, srcPath, false,config);
            }catch(Exception e){

                e.printStackTrace();
            }
        }
    }
}
