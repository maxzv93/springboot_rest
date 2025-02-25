package com.max.spring.springboot.springboot_rest.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;


/**
 * Kafka сервис.
 *
 * @author ZuevMYu
 * @since 03.11.2024
 */
public class KafkaServiceImpl implements KafkaService {

    //метод отправки сообщений в брокер kafka.
    @Override
    public void enqueueKafkaMessage(String message) {
                int MSG_COUNT = 2;
                String HOST = "localhost:9092";
                String TOPIC = "my_topic";
                String USER = "admin";
                String PASS = "admin";
                String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
                String jaasCfg = String.format(jaasTemplate, USER, PASS);
                String KEY = "key";
                String serializer = StringSerializer.class.getName();
                Properties props = new Properties();
                props.put("bootstrap.servers", HOST);
//                props.put("acks", "all");
                props.put("key.serializer", serializer);
                props.put("value.serializer", serializer);
//                props.put("security.protocol", "SASL_SSL");
//                props.put("sasl.mechanism", "SCRAM-SHA-512");
//                props.put("sasl.jaas.config", jaasCfg);
//                props.put("ssl.truststore.location", TS_FILE);
//                props.put("ssl.truststore.password", TS_PASS);
                Producer<String, String> producer = new KafkaProducer<>(props);
                try {
                    for (int i = 1; i <= MSG_COUNT; i++){
                        producer.send(new ProducerRecord<String, String>(TOPIC, KEY, message)).get();
                        System.out.println("Test message " + i);
                    }
                    producer.flush();
                    producer.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                    producer.close();
                }
    }
}