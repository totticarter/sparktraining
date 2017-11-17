package com.tencent.cubeli.java.compress;

/**
 * Created by waixingren on 09/11/2017.
 */

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */


public class Bzip2T {

    public static int buffersize = 32;
    public static void main(String[] args) throws  IOException{


        Bzip2T bzip2T = new Bzip2T();

        //1.先打开这行代码，对data目录下得http-request.log文件进行压缩，生成bz2压缩文件http-request.log.bz2
//        bzip2T.compressUseBzip2("data/http-request.log");

        //2.再打开这行代码，对上一步压缩的文件进行解压，测试解压流程，生成解压文件http-request.log.bz2.org，和原始文件http-request.log进行比较，确认一致
//        bzip2T.uncompressUseBzip2FromFile("data/http-request.log.bz2");


        //3.再打开这行代码
        // 因为我没有kafka的压缩消息，所以这里使用读压缩文件的方式进行模拟
        bzip2T.processMessageFromKafka();
    }

    public void processMessageFromKafka(){

        try{

            //开始模拟从kafka接收到消息
            String fileName = "data/http-request.log.bz2";
            File f = new File(fileName);
            InputStream fin = Files.newInputStream(Paths.get(fileName));
            BufferedInputStream in = new BufferedInputStream(fin);
            int fileLength = (int)f.length();
            byte[] compressedBytesStream = new byte[fileLength];
            in.read(compressedBytesStream, 0, fileLength); //因为这个文件是一个压缩文件，所以读到compressedBytesStream是压缩的字节流，可以模仿从kafka接收到得消息


            //开始从这个内存字节流解压(可以把这个方法封装到工具类中，在sparkstreaming中调用)
            uncompressUseBzip2FromBytes(compressedBytesStream);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void uncompressUseBzip2FromFile(String fileName) {

        try{

            InputStream fin = Files.newInputStream(Paths.get(fileName));
            BufferedInputStream in = new BufferedInputStream(fin);
            OutputStream out = Files.newOutputStream(Paths.get(fileName+".org"));
            BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in);
            final byte[] buffer = new byte[buffersize];
            int n = 0;
            while (-1 != (n = bzIn.read(buffer))) {
                out.write(buffer, 0, n);
            }
            out.close();
            bzIn.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void uncompressUseBzip2FromBytes(byte[] bytes){


        try{

            //用文件解压是构造一个FileInputStream，用字节流解压是构造一个ByteArrayInputStream，他们的基类都是InputStream
            InputStream fin = new ByteArrayInputStream(bytes);
            BufferedInputStream in = new BufferedInputStream(fin);
            String outputfileName = "data/uncompressedFromMessage";
            OutputStream out = Files.newOutputStream(Paths.get(outputfileName+".org"));

            //因为需要在spark内存中处理，所以解压后的数据不能写磁盘文件，这里构造ByteArrayOutputStream对象，在内存中进行存储
            OutputStream memOut = new ByteArrayOutputStream();
            BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in);
            final byte[] buffer = new byte[buffersize];
            int n = 0;

            while (-1 != (n = bzIn.read(buffer))) {
                memOut.write(buffer, 0, n);
            }

            ByteArrayOutputStream logdataStream = (ByteArrayOutputStream)memOut;
            String logData = logdataStream.toString();

            //得到日志数据，开始处理
            System.out.println(logData);
            String[] logLines = logData.split("\\n");
            memOut.close();
            bzIn.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void compressUseBzip2(String fileName){

        try{

            InputStream in = Files.newInputStream(Paths.get(fileName));
            OutputStream fout = Files.newOutputStream(Paths.get(fileName+".bz2"));
            BufferedOutputStream out = new BufferedOutputStream(fout);
            BZip2CompressorOutputStream bzOut = new BZip2CompressorOutputStream(out);
            final byte[] buffer = new byte[buffersize];
            int n = 0;
            while (-1 != (n = in.read(buffer))) {
                bzOut.write(buffer, 0, n);
            }
            bzOut.close();
            in.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

