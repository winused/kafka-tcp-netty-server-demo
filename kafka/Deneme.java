package io.conduktor.demos.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import java.util.Properties;



public class Deneme {


    private static final Logger log = LoggerFactory.getLogger(Deneme.class);

    public static void main(String[] args) {
        log.info("I am a Kafka Topic Lister");

        String bootstrapServers = "127.0.0.1:9092";

        Map<String, List<PartitionInfo>> topics;


        String groupId = "my-first-trial";

        // create consumer configs
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        System.out.println("1.parça");
        System.out.printf("topic = %s", consumer.listTopics());
        System.out.println();
        System.out.println("2.parça");
        topics = consumer.listTopics();

        System.out.println(topics);
        consumer.close();

    }
}
