package com.tencent.cubeli.kafka.producer;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.javaapi.producer.Producer;

import java.util.Properties;

/**
 * Created by waixingren on 10/8/17.
 * https://cwiki.apache.org/confluence/display/KAFKA/0.8.0+Producer+Example
 * 可以在控制台消费到
 */
public class ProducerT {

    public static void main(String[] args) {

        Properties props = new Properties();

        props.put("metadata.broker.list", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);

        KeyedMessage<String, String> data = new KeyedMessage<String, String>("tpch", "key", "hello world");
        producer.send(data);
    }
}
