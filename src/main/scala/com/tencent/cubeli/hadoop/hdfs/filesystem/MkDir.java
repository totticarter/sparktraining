package com.tencent.cubeli.hadoop.hdfs.filesystem;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

/**
 * Created by waixingren on 10/6/17.
 */
public class MkDir {

    public static void main(String[] args) throws Exception{

        Configuration config = new Configuration();
        FileSystem hdfs = FileSystem.get(new URI("hdfs://localhost:9000"), config, "waixingren");

        String tmpDir = "hdfs://localhost:9000/tmp/test/testdir";
        Path path = new Path(tmpDir);
        hdfs.mkdirs(path);
    }
}
