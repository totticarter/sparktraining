package com.tencent.cubeli.hadoop.hdfs.filesystem;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.net.URI;

/**
 * Created by waixingren on 10/6/17.
 */
public class List {

    public static void main(String[] args) throws  Exception{

        Configuration config = new Configuration();
        FileSystem hdfs = FileSystem.get(new URI("hdfs://localhost:9000"), config, "waixingren");

        String tmpDir = "hdfs://localhost:9000/tmp/test/";
        Path path = new Path(tmpDir);
        FileStatus[] files = hdfs.listStatus(path);

//        private Path path;
//        private long length;
//        private boolean isdir;
//        private short block_replication;
//        private long blocksize;
//        private long modification_time;
//        private long access_time;
//        private FsPermission permission;
//        private String owner;
//        private String group;
//        private Path symlink;
        for(int i = 0; i < files.length; i++){

            System.out.println(files[i].getPath().toString());
        }


        FSDataInputStream fsdis =hdfs.open(new  Path("hdfs://localhost:9000/tmp/test/readmefromlocal-bk.txt"));
        IOUtils.copyBytes(fsdis, System.out, 4096, false);
    }
}
