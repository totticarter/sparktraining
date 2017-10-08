package com.tencent.cubeli.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by waixingren on 10/8/17.
 * http://blog.csdn.net/qq_35799003/article/details/52238744
 * 无法在控制台消费到
 */
public class ProducerCallback {

    private static KafkaProducer kafkaProducer = null;

    public static void main(String[] args) {

        try {

            String configFile = "/Users/waixingren/software/kafka/kafka-0.8.2.2-src/config/producer.properties";
            Properties properties = new Properties();
            properties.load(new FileInputStream(configFile));
            kafkaProducer = new KafkaProducer(properties);


            ProducerRecord record;
            String message = "hello world";
            String topic = "tpch";
            record = new ProducerRecord<>(topic, "", message);
            kafkaProducer.send(record, new SendCallback(record, 0));

        }catch(Exception e){

            e.printStackTrace();
        }

    }

    static class SendCallback implements Callback {
        ProducerRecord<String, String> record;
        int sendSeq = 0;

        public SendCallback(ProducerRecord record, int sendSeq) {
            this.record = record;
            this.sendSeq = sendSeq;
        }
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            //send success
            if (null == e) {
                String meta = "topic:" + recordMetadata.topic() + ", partition:"
                        + recordMetadata.topic() + ", offset:" + recordMetadata.offset();
                System.out.println(("send message success, record:" + record.toString() + ", meta:" + meta));
                return;
            }
            //send failed
            System.out.println(("send message failed, seq:" + sendSeq + ", record:" + record.toString() + ", errmsg:" + e.getMessage()));
            if (sendSeq < 1) {
                kafkaProducer.send(record, new SendCallback(record, ++sendSeq));
            }
        }
    }
}
