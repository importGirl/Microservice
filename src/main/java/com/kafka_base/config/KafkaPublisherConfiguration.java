package com.kafka_base.config;

import com.kafka_base.model.Message;
import com.kafka_base.model.MessageEncoder;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangdg
 * @ClassName:
 * @Description:
 * @date 2017-06-11 00:50:22
 */
@Configuration
public class KafkaPublisherConfiguration {
    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, MessageEncoder.class.getName());
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 5000);
//        props.put("value.serializer", MessageEncoder.class.getName());
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    @Bean
    public ProducerFactory<String, Message> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate() {
        return new KafkaTemplate<String, Message>(producerFactory());
    }


}
