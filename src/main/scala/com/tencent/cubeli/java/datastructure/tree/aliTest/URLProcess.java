package com.tencent.cubeli.java.datastructure.tree.aliTest;

import java.io.*;
import java.util.*;

/**
 * 整体思路：
 * 1.先通过内存把两个大文件分块排序，然后归并排序两个小文件，完成原始文件的排序
 * 2.对排序后的两个文件做归并比对，遇到都出现在两个文件中得url统计出现次数，保存出现次数top30的url
 *
 * 时间复杂度O(n*logn)，空间复杂度O(n)
 */
public class URLProcess {

    public static long lineNumber = 1024*1024*1024L;
    public static String[] fileNames = {"file100g-1", "file100g-2"};
    public static void main(String[] args) throws IOException {

        int fileIdx = 1;
        for(String fileName : fileNames){

            fileIdx++;
            FileReader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            Set<String> set = new TreeSet<>();
            long readCount = 0;
            //读文件前半部分+sort+write
            while ((str = br.readLine()) != null) {
                if(readCount++ == lineNumber/2){
                    break;
                }
                set.add(str);
            }

            String sortFile1Name = fileName+"-sorted1";
            FileWriter writer1 = new FileWriter(sortFile1Name);
            BufferedWriter bw1 = new BufferedWriter(writer1);
            for(String url: set){
                bw1.write(url+"/n");
            }

            //读文件后半部分+sort+write
            set.clear();
            while ((str = br.readLine()) != null) {
                set.add(str);
            }
            String sortFile2Name = fileName+"-sorted2";
            FileWriter writer2 = new FileWriter(sortFile2Name);
            BufferedWriter bw2 = new BufferedWriter(writer2);
            for(String url: set){
                bw2.write(url+"/n");
            }

            br.close();
            bw1.close();
            bw2.close();

            //进行归并排序并写文件
            mergeSort(sortFile1Name, sortFile2Name, String.valueOf(fileIdx));

        }

        //归并排序处理两个排序文件，遇到url相同则开始统计url的出现次数
        FileReader reader1 = new FileReader("sorted-1");
        BufferedReader br1 = new BufferedReader(reader1);
        FileReader reader2 = new FileReader("sorted-2");
        BufferedReader br2 = new BufferedReader(reader2);

        String urlFrom1 = br1.readLine();
        String urlFrom2 = br2.readLine();

        Map<Integer, String> countToUrl = new TreeMap<>();
        while(true){

            if(urlFrom1 == null && urlFrom2 == null){
                break;
            }

            if(urlFrom1 != null && urlFrom2 != null){
                if(urlFrom1.equals(urlFrom2)){
                    String sameUrl = urlFrom1;
                    List<String> newLines = insertIntoMap(countToUrl, br1, br2, sameUrl);
                    urlFrom1 = newLines.get(0);
                    urlFrom2 = newLines.get(1);
                }else if(urlFrom1.compareTo(urlFrom2) < 0){
                    urlFrom1 = br1.readLine();
                }else{
                    urlFrom2 = br2.readLine();
                }
            }

            if(urlFrom1 == null && urlFrom2 != null){
                urlFrom2 = br2.readLine();
            }else if(urlFrom2 == null && urlFrom1 != null){
                urlFrom1 = br1.readLine();
            }


        }
    }

    public static List<String> insertIntoMap(Map<Integer, String> countToUrl, BufferedReader br1, BufferedReader br2, String sameUrl){

        int count = 2;
        String urlFrom1 = null;
        String urlFrom2 = null;
        List<String> newLine = new ArrayList<>();
        try{
            while((urlFrom1 = br1.readLine()) != null && !urlFrom1.equals(sameUrl)){
                count++;
            }
            while((urlFrom2 = br2.readLine()) != null && !urlFrom2.equals(sameUrl)){
                count++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                br1.close();
                br2.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        newLine.add(urlFrom1);
        newLine.add(urlFrom2);


        //只存储count top30的url
        if(countToUrl.size() <= 30){
            countToUrl.put(count, sameUrl);
        }else{
            Integer minCount = Collections.min(countToUrl.keySet());
            if(count > minCount){
                countToUrl.remove(minCount);
                countToUrl.put(count, sameUrl);
            }
        }
        return newLine;
    }

    public static void mergeSort(String file1, String file2, String fileIdx){


        BufferedReader br1 = null;
        BufferedReader br2 = null;
        FileWriter writer = null;
        try{

            FileReader reader1 = new FileReader(file1);
            br1 = new BufferedReader(reader1);

            FileReader reader2 = new FileReader(file2);
            br2 = new BufferedReader(reader2);

            String sortFileName = "sorted-" + fileIdx;
            writer = new FileWriter(sortFileName);

            String urlFrom1 = br1.readLine();
            String urlFrom2 = br2.readLine();
            while(true){

                if(urlFrom1 == null && urlFrom2 == null){
                    break;
                }

                if(urlFrom1 != null && urlFrom2 != null){
                    if(urlFrom1.equals(urlFrom2)){
                        writer.write(urlFrom1 + "/n" + urlFrom2);
                        urlFrom1 = br1.readLine();
                        urlFrom2 = br2.readLine();
                    }else if(urlFrom1.compareTo(urlFrom2) < 0){
                        writer.write(urlFrom1+"/n");
                        urlFrom1 = br1.readLine();
                    }else{
                        writer.write(urlFrom2+"/n");
                        urlFrom2 = br2.readLine();
                    }
                }

                if(urlFrom1 == null && urlFrom2 != null){
                    writer.write(urlFrom2+ "/n");
                    urlFrom2 = br2.readLine();
                }else if(urlFrom2 == null && urlFrom1 != null){
                    writer.write(urlFrom1 + "/n");
                    urlFrom1 = br1.readLine();
                }
            }


        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                br1.close();
                br2.close();
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
