package com.tencent.cubeli.hadoop.hdfs.filesystem;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

/**
 * Created by waixingren on 10/6/17.
 */
public class Move {

    public static void main(String[] args) {

        try{

            //moveFromLocalFile FileSystem
            //    public void moveFromLocalFile(Path src, Path dst)
            //    throws IOException {
            //            copyFromLocalFile(true, src, dst);
            //        }
            //    }

            //moveToLocalFile FileSystem
            //copyToLocalFile(true, src, dst);

            //源文件
            String fsrc = "hdfs://localhost:9000/tmp/test/readmefromlocal.txt";
            //目标文件
            String destfile = "hdfs://localhost:9000/tmp/test/readmefromlocal-bk2.txt";

            Configuration config = new Configuration();
            // 程序配置
            config.set("fs.default.name", "hdfs://localhost:9000");

            FileSystem hdfs = FileSystem.get(new URI("hdfs://localhost:9000"), config, "waixingren");
            Path srcPath = new Path(fsrc);
            Path destPath = new Path(destfile);

            hdfs.rename(srcPath, destPath);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
