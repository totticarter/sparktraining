package com.tencent.cubeli.kafka.producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by waixingren on 10/8/17.
 * https://cwiki.apache.org/confluence/display/KAFKA/0.8.0+Producer+Example
 * 可以在控制台消费到
 */
public class ProducerT {

    public static void main(String[] args) throws IOException,InterruptedException{

        Properties props = new Properties();

        props.put("metadata.broker.list", "localhost:9090");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);

        File ordersFile = new File("/Users/waixingren/software/tpch/tpch-dbgen/orders.tbl");
        BufferedReader reader = new BufferedReader(new FileReader(ordersFile));
        String oneLine = null;
        int count = 0;
        while((oneLine = reader.readLine()) != null){

            if(count++%100 == 0){
                System.out.println("Sent " + count + " messages to kafka!");
            }
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("tpch2.orders", "key", oneLine);
            producer.send(data);
            Thread.sleep(10);
        }
        reader.close();
    }
}
