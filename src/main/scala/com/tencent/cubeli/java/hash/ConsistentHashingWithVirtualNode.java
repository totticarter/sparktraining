package com.tencent.cubeli.java.hash;

/**
 * Created by waixingren on 09/03/2018.
 */

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ConsistentHashingWithVirtualNode {

    //待添加入Hash环的服务器列表
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};

    //真实结点列表,考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList会更好
    private static List<String> realNodes = new LinkedList<String>();

    //虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<Integer, String>();

    //虚拟节点的数目，这里写死，为了演示需要，一个真实结点对应5个虚拟节点
    private static final int VIRTUAL_NODES = 5;

    public static Map<String, Integer> serverCount = new HashMap<>();

    static{
        //先把原始的服务器添加到真实结点列表中
        for(int i=0; i<servers.length; i++)
            realNodes.add(servers[i]);

        //再添加虚拟节点，遍历LinkedList使用foreach循环效率会比较高
        for (String str : realNodes){
            for(int i=0; i<VIRTUAL_NODES; i++){
                String virtualNodeName = str + "&&VN" + String.valueOf(i);
                int hash = getHash(virtualNodeName);
                System.out.println("虚拟节点[" + virtualNodeName + "]被添加, hash值为" + hash);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
        System.out.println();
    }

    //使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
    private static int getHash(String str){
        final int p = 16777619;
        int hash = (int)2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    //得到应当路由到的结点
    private static String getServer(String key){
        //得到该key的hash值
        int hash = getHash(key);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        String virtualNode;
        if(subMap.isEmpty()){
            //如果没有比该key的hash值大的，则从第一个node开始
            Integer i = virtualNodes.firstKey();
            //返回对应的服务器
            virtualNode = virtualNodes.get(i);
        }else{
            //第一个Key就是顺时针过去离node最近的那个结点
            Integer i = subMap.firstKey();
            //返回对应的服务器
            virtualNode = subMap.get(i);
        }
        //virtualNode虚拟节点名称要截取一下
        if(StringUtils.isNotBlank(virtualNode)){
            return virtualNode.substring(0, virtualNode.indexOf("&&"));
        }
        return null;
    }

    public static void deleteServer(String ip){

        for (Iterator<Map.Entry<Integer, String>> it = virtualNodes.entrySet().iterator(); it.hasNext();){
            Map.Entry<Integer, String> item = it.next();
            if(item.getValue().startsWith(ip))
                it.remove();
        }
    }

    public static Object[] getKeysFromFile(){

        File file = new File("/Users/waixingren/bigdata-java/spark/sparkproj/data/hash/README.md");
        Set<String> r = new HashSet<>();
        try{

            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = bufferedReader.readLine()) != null){

                String words[] = line.split(" ");
                for(String word : words){
                    r.add(word);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return r.toArray();

    }



    public static void main(String[] args){

//        //正常添加object
//        String[] keys = {"a", "b", "c", "d", "e", "f","g", "h", "i"};
//        for(int i=0; i<keys.length; i++)
//            System.out.println("[" + keys[i] + "]的hash值为" + getHash(keys[i]) + ", 被路由到结点[" + getServer(keys[i]) + "]");
//
//        //删除物理节点
//        System.out.println("删除一个物理节点");
//        deleteServer("192.168.0.0");
//        for(int i=0; i<keys.length; i++)
//            System.out.println("[" + keys[i] + "]的hash值为" + getHash(keys[i]) + ", 被路由到结点[" + getServer(keys[i]) + "]");



        Object[] keys = getKeysFromFile();
        deleteServer("192.168.0.0");
        for(int i=0; i<keys.length; i++){

            String server = getServer((String)keys[i]);
            Integer count = serverCount.get(server);
            if(count != null){
                serverCount.put(server, ++count);
            }else{
                serverCount.put(server, new Integer(0));
            }
            System.out.println("[" + keys[i] + "]的hash值为" + getHash((String)keys[i]) + ", 被路由到结点[" + server + "]");
        }

        for(Map.Entry<String, Integer> entry: serverCount.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

//        //删除物理节点
//        System.out.println("删除一个物理节点");
//        deleteServer("192.168.0.0");
//        for(int i=0; i<keys.length; i++)
//            System.out.println("[" + keys[i] + "]的hash值为" + getHash(keys[i]) + ", 被路由到结点[" + getServer(keys[i]) + "]");



    }
}
